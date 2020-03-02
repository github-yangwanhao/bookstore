package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class GoodsDto {

    private Long id;

    private Long price;

    private Double priceDouble;

    private Integer stock;

    private Integer category;

    private String title;

    private String images;

    private String tags;

    private String detail;

    private String detailImages;

}
