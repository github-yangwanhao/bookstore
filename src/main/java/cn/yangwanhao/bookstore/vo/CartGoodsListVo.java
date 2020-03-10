package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 13:54
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartGoodsListVo implements Serializable {

    private static final long serialVersionUID = -8604916878822792581L;

    private Long cartId;

    private Long goodsId;

    private String goodsTitle;

    private String goodsCoverImageUrl;

    private Integer goodsNum;

    private Long price;

    private Double priceDouble;

    private Double goodsTotalPrice;

    private Integer goodsIsDeleted;

    private Integer goodsIsOffTheShelves;

    private Integer goodsStatus;

}
