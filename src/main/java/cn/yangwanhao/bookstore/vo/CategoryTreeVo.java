package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杨万浩
 * @description CategoryTreeVo类
 * @date 2020/3/6 19
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryTreeVo implements Serializable {

    private Integer id;

    private String name;

    // private List<CategoryTreeVo> children;

}
