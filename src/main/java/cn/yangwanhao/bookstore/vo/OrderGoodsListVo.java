package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨万浩
 * @description OrderGoodsListVo类
 * @date 2020/3/12 14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderGoodsListVo implements Serializable {

    private Long goodsId;

    private String img;

    private String goodsTitle;

    private Long price;

    private Double priceDouble;

    private Integer goodsNum;

    private Double goodsTotalPrice;

}
