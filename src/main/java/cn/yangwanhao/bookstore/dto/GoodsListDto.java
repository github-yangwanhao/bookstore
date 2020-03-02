package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.support.BasePageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/11 20:13
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class GoodsListDto extends BasePageDto {

    private String title;

    private Integer goodsStatus;

    private Integer orderByStock;

    private Integer orderByGoodsBuyVersion;

}
