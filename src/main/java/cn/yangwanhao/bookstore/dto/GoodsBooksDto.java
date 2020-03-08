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

}
