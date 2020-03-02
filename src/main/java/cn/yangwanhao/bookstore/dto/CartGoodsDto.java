package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/9 17:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartGoodsDto implements Serializable {

    private Long goodsId;

    private Integer goodsNum;

    private Integer operate;

}
