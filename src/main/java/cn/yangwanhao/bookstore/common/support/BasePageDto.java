package cn.yangwanhao.bookstore.common.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * BasePageDto
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/28 11:14
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasePageDto implements Serializable {

    private static final long serialVersionUID = -3133418092766629129L;

    /**
     * 页码
     */
    private Integer pageNum = 1;

    /**
     * 页面大小
     */
    private Integer pageSize = 10;

}
