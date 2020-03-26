package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author 杨万浩
 * @description CategoryMenuTreeVo类
 * @date 2020/3/23 10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMenuTreeVo implements Serializable {

    private Integer id;

    private String name;

    private List<CategoryMenuTreeVo> children;

}
