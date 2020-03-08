package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.util.BigDecimalUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/11 10:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsDto implements Serializable {

    private Long id;

    /**
     * 价格:(单位:元)
     */
    private Long price;

    /**
     * 价格:(单位:分)
     */
    private Double priceDouble;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 分类
     */
    private Integer category;

    /**
     * 名称
     */
    private String title;

    /**
     * 主图
     */
    private String images;

    /**
     * 标签
     */
    private String tags;

    /**
     * 详情
     */
    private String detail;

    private String detailImages;

    public Long getPrice() {
        return BigDecimalUtils.movePointRight(Double.toString(this.priceDouble), 2).longValue();
    }

}
