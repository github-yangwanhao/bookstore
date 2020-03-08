package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.support.BasePageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @description GoodsSearchDto类
 * @date 2020/3/8 16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsSearchDto extends BasePageDto {

    private String q;

}
