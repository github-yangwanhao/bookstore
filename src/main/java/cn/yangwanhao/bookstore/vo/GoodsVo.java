package cn.yangwanhao.bookstore.vo;

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
 * @date 2019/12/9 17:38
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo implements Serializable {

    private Long id;

    /*private String idStr;*/

    private Long price;

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

    private String thumbnailImage;

    /**
     * 标签
     */
    private String tags;

    /**
     * 详情
     */
    private String detail;

    /**
     * 作者
     */
    private String author;

    /**
     * 出版社
     */
    private String publisher;

    /**
     * ISBN
     */
    private String isbn;

    private Long goodsVersion;

   /* public String getIdStr() {
        return String.valueOf(this.id);
    }*/

}
