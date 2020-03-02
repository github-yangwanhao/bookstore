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
public class GoodsBooksDto extends GoodsDto {

    private String author;

    private String publisher;

    private String isbn;

}
