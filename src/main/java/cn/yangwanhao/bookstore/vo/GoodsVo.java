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
 * @date 2019/12/9 17:38
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsVo implements Serializable {

    private Long goodsId;

    private Long goodsVersion;

    private Long priceLong;

    private Double priceDouble;

}
