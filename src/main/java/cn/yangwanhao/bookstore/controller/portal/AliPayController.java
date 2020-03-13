package cn.yangwanhao.bookstore.controller.portal;

import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;
import cn.yangwanhao.bookstore.common.enums.TradeRecordTypeEnum;
import cn.yangwanhao.bookstore.common.exception.GlobalException;
import cn.yangwanhao.bookstore.common.support.BaseController;
import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import cn.yangwanhao.bookstore.config.AliPaySandBoxConfig;
import cn.yangwanhao.bookstore.dto.TradeRecordDto;
import cn.yangwanhao.bookstore.entity.Order;
import cn.yangwanhao.bookstore.service.OrderService;
import cn.yangwanhao.bookstore.service.TradeRecordService;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author 杨万浩
 * @description AliPayController类
 * @date 2020/3/13 15
 */
@Controller
@RequestMapping("/store/aliPay")
public class AliPayController extends BaseController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private TradeRecordService tradeRecordService;

    @Value("${aliPay.returnUrl}")
    private String aliPayReturnUrl;

    // 创建AliPayClient
    AliPaySandBoxConfig config = new AliPaySandBoxConfig();

    @RequestMapping("/toPay")
    @ResponseBody
    public String payMoney(String orderNo) throws AlipayApiException {
        // 获取订单详情
        Order order = orderService.getOrderByOrderNo(orderNo);

        AlipayClient client = new DefaultAlipayClient(
                config.getGateway(),
                config.getAppId(),
                config.getAliPayPrivateKey(),
                config.getFormat(),
                config.getCharset(),
                config.getAliPayPublicKey(),
                config.getSignType()
        );
        AlipayTradePagePayRequest aliPayRequest = new AlipayTradePagePayRequest();
        aliPayRequest.setReturnUrl(aliPayReturnUrl);
        // TODO 页面跳转同步通知页面路径,需http://格式的完整路径,必须外网可以正常访问aliPayRequest.setNotifyUrl(config.notify_url);
        //商户订单号,商户网站订单系统中唯一订单号,必填
        String outTradeNo = orderNo;
        //付款金额,必填
        DecimalFormat format = new DecimalFormat("0.00");
        String totalAmount = format.format(BigDecimalUtils.movePointLeft(String.valueOf(order.getTotalPrice()), 2));
        //订单名称,必填
        String subject = orderNo;
        //商品描述,可空
        String body = "";
        // 该笔订单允许的最晚付款时间,逾期将关闭交易.取值范围:1m~15d.m-分钟,h-小时,d-天,1c-当天(1c-当天的情况下,无论交易何时创建,都在0点关闭).该参数数值不接受小数点,如1.5h,可转换为90m.
        String timeout_express = "30m";
        String requestBody = "{\"out_trade_no\":\""+ outTradeNo +"\","
                + "\"total_amount\":\""+ totalAmount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\""+ timeout_express +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}";
        aliPayRequest.setBizContent(requestBody);
        //请求
        String result = client.pageExecute(aliPayRequest).getBody();
        return result;
    }

    @RequestMapping("/return")
    public String aliPayReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        boolean verifyResult = rsaCheckV1(request);
        //验证成功
        if(verifyResult){
            //商户订单号
            String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String tradeNo = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            Order order = orderService.getOrderByOrderNo(orderNo);
            TradeRecordDto dto = new TradeRecordDto();
            dto.setUserId(order.getUserId());
            dto.setOrderId(order.getId());
            dto.setOrderNo(orderNo);
            dto.setMoney(order.getTotalPrice());
            dto.setTradeType(TradeRecordTypeEnum.PAY.getType());
            dto.setAliPayTradeNo(tradeNo);
            // 添加交易记录
            tradeRecordService.saveTradeRecord(dto);
            // 修改订单状态
            orderService.customerPaidOrder(orderNo);
            return "redirect:/store/order/detail/"+orderNo;
        }else{
            throw new GlobalException(ErrorCodeEnum.O5009014);
        }
    }

    @RequestMapping("notify")
    public String notify(HttpServletRequest request) throws Exception{
        // 一定要验签，防止黑客篡改参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        boolean flag = this.rsaCheckV1(request);
        if (flag) {
            /**
             *
             * 商户需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
             * 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
             * 同时需要校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email），
             *
             * 上述有任何一个验证不通过，则表明本次通知是异常通知，务必忽略。
             * 在上述验证通过后商户必须根据支付宝不同类型的业务通知，正确的进行不同的业务处理，并且过滤重复的通知结果数据。
             * 在支付宝的业务通知中，只有交易通知状态为TRADE_SUCCESS或TRADE_FINISHED时，支付宝才会认定为买家付款成功。
             */

            //交易状态
            String tradeStatus = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
            // 商户订单号
            String orderNo = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");
            // TRADE_FINISHED(表示交易已经成功结束，并不能再对该交易做后续操作);
            // TRADE_SUCCESS(表示交易已经成功结束，可以对该交易做后续操作，如：分润、退款等);
            if(tradeStatus.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
                //如果没有签约可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。
            } else if (tradeStatus.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，
                // 并判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），并执行商户的业务程序
                //请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //如果签约的是可退款协议，那么付款完成后，支付宝系统发送该交易状态通知。

            }
            return "redirect:/store/order/detail/"+orderNo;
        }
        throw new GlobalException(ErrorCodeEnum.O5009014);
    }

    private boolean rsaCheckV1(HttpServletRequest request){
        // https://docs.open.alipay.com/54/106370
        // 获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map requestParams = request.getParameterMap();
        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        try {
            boolean verifyResult = AlipaySignature.rsaCheckV1(params,
                    config.getAliPayPrivateKey(),
                    config.getCharset(),
                    config.getSignType());
            return verifyResult;
        } catch (AlipayApiException e) {
            return true;
        }
    }

}
