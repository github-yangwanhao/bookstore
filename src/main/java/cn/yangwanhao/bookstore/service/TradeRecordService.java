package cn.yangwanhao.bookstore.service;

import cn.yangwanhao.bookstore.dto.TradeRecordDto;
import cn.yangwanhao.bookstore.entity.TradeRecord;

/**
 * @author 杨万浩
 * @description TradeService类
 * @date 2020/3/13 17
 */
public interface TradeRecordService {

    /**
     * Description: 插入交易表
     * @param dto dto
     * @return
     * @author 杨万浩
     * @date 2020/3/13 19:57
     */
    Integer paidSuccess(TradeRecordDto dto);

    /**
     * Description: 通过orderNo获取付款成功的那一条数据
     * @param orderNo orderNo
     * @return
     * @author 杨万浩
     * @date 2020/3/15 10:27
     */
    TradeRecord getPaidRecordByOrderNo(String orderNo);

    /**
     * Description: 退款成功记录
     * @param dto dto
     * @return
     * @author 杨万浩
     * @date 2020/3/15 12:26
     */
    Integer refundSuccess(TradeRecordDto dto);

}
