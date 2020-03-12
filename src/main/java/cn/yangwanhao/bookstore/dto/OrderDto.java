package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.annotation.NotBlank;
import cn.yangwanhao.bookstore.common.constant.GlobalValidantConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/24 16:57
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {

    private static final long serialVersionUID = 7205886689800645186L;

    @NotBlank(message = GlobalValidantConstant.O1007)
    private String goodsId;

    @NotBlank(message = GlobalValidantConstant.O1008)
    private String goodsNum;

    private Long totalPrice;

    /**
     * 当前登录人id
     */
    private Long userId;

    private Long addressId;

}
