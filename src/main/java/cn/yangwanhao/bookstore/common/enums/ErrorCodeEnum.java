package cn.yangwanhao.bookstore.common.enums;

import lombok.Getter;

/**
 *
 * 异常信息枚举类
 *
 * @author 杨万浩
 * @date 2019/10/14 10:22
 * @version 1.0.0
 */

public enum  ErrorCodeEnum {

    /**
     * 全局异常以 G500 开头
     * 用户异常以 U500 开头
     * 商品异常以 I500 开头
     * 订单异常以 O500 开头
     * 购物车异常以 C500 开头
     */

    /**
     * 全局异常以 G500 开头,101为第一个
     */
    G500101(500101, "参数异常"),
    G500102(500102, "参数不能为空"),
    /**
     * 不能为空系列以1001开头
     */
    U5001001(5001001, "用户名不能为空"),
    U5001002(5001002, "手机号不能为空"),
    U5001003(5001003, "密码不能为空"),
    U5001004(5001004, "确认密码不能为空"),
    U5001005(5001005, "新密码不能为空"),
    U5001006(5001006, "登录名不能为空"),
    U5001007(5001007, "验证码不能为空"),
    U5001008(5001008, "邮箱不能为空"),
    /**
     * 格式不正确系列以2001开头
     */
    U5002001(5002001, "手机号格式不正确"),
    U5002002(5002002, "邮箱格式不正确"),
    U5002003(5002003, "数字格式不正确"),
    /**
     * 不存在系列以3001开头
     */
    U5003001(5003001, "验证码已过期"),
    U5003002(5003002, "未找到收货地址"),
    C5003003(5003003, "购物车中未找到该商品"),
    U5003004(5003004, "未找到该用户"),
    O5003005(5003005, "未找到该订单"),
    U5003006(5003006, "未找到该收货地址"),
    C5003007(5003007, "购物车是空的"),
    O5003008(5003008, "未找到该订单,订单号=%s"),
    O5003009(5003009, "退款失败,未找到该订单的支付记录,订单号=%s"),
    /**
     * 已存在系列以4001开头
     */
    U5004001(5004001, "登录名已经存在"),
    /**
     * 添加失败系列以5001开头
     */
    I5005001(5005001, "添加商品失败"),
    /**
     * 修改失败系列以6001开头
     */
    I5006001(5006001, "商品修改失败"),
    /**
     * 删除失败系列以7001开头
     */
    I5007001(5007001, "删除商品失败"),
    I5007002(5007002, "删除商品分类失败,因为该分类下还有商品存在"),
    /**
     * 其他：9001开头
     */
    U5009001(5009001, "用户名或密码错误"),
    U5009002(5009002, "验证码错误"),
    U5009003(5009003, "两次输入的密码不一致"),
    U5009004(5009004, "新密码和旧密码不能相同"),
    I5009005(5009005, "商品库存不足"),
    I5009006(5009006, "请勿重复操作"),
    C5009007(5009007, "该宝贝不能减少了呦"),
    O5009008(5009008, "商品价格发生了变动,请确认后重新下单"),
    U5009009(5009009, "验证码已过期"),
    I5009010(5009010, "上传的文件不是图片!"),
    U5009011(5009011, "账户已被锁定!"),
    U5009012(5009012, "旧密码错误"),
    O5009013(5009013, "订单不可发货,订单编号=%s"),
    O5009014(5009014, "获取支付宝交易回执失败"),
    O5009015(5009015, "您还没有填写收货地址,清闲前往个人中心完善信息"),
    O5009016(5009016, "退款失败"),
    O5009017(5009017, "该订单不可付款,订单号=%s"),
    O5009018(5009018, "该订单不可取消,订单号=%s"),
    O5009019(5009019, "该订单不可确认收货,订单号=%s"),
    /**
     * 几个特殊的异常
     */
    U400(400, "语法错误"),
    U401(401, "未登录"),
    U403(403, "服务端禁止访问"),
    U404(404, "访问的资源不存在"),
    U405(405, "请求方法错误"),
    ;
    @Getter
    private int code;
    @Getter
    private final String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorCodeEnum getByCode(int code) {
        for (ErrorCodeEnum e : ErrorCodeEnum.values()) {
            if (e.getCode() == code) {
                return e;
            }
        }
        return null;
    }
}
