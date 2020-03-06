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
 * @date 2019/11/28 16:02
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GoodsCategoryTreeVo implements Serializable {

    private static final long serialVersionUID = 4004820354997596163L;

    private Integer id;

    private String name;

    private Integer parentId;

    private String hasParent;

    private String hasChildren;

    private Integer sort;

}
