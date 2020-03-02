package cn.yangwanhao.bookstore.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 11:55
 */

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GlobalValidantConstant {

    /**
     * 全局验证提示信息以 G 开头
     * 用户验证提示信息以 U 开头
     * 商品验证提示信息以 I 开头
     * 订单验证提示信息以 O 开头
     * 购物车验证提示信息以 C 开头
     */

    /**
     * 不能为空系列以1001开头
     */
    public static final String U1001 = "手机号不能为空";
    public static final String U1002 = "收货人地址不能为空";
    public static final String U1003 = "收货人姓名不能为空";
    public static final String I1004 = "广告标题不能为空";
    public static final String I1005 = "广告指向的url不能为空";
    public static final String I1006 = "广告图片不能为空";
    public static final String O1007 = "订单的商品id不能为空";
    public static final String O1008 = "订单的商品数量不能为空";
    /**
     * 格式不正确系列以2001开头
     */
    public static final String U2001 = "手机号格式不正确";
    /**
     * 不存在系列以3001开头
     */
    /**
     * 已存在系列以4001开头
     */
    /**
     * 添加失败系列以5001开头
     */
    /**
     * 修改失败系列以6001开头
     */
    /**
     * 删除失败系列以7001开头
     */
    /**
     * 其他：9001开头
     */
    /**
     * 几个特殊的提示
     */

}
