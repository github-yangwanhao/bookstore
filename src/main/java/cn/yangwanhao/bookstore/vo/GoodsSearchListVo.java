package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨万浩
 * @description GoodsSearchListVo类
 * @date 2020/3/8 16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSearchListVo implements Serializable {

    private Long id;

    private String title;

    private String image;

    private Long price;

    private Double priceDouble;

}
