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
 * @date 2019/12/26 17:55
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DictionaryVo implements Serializable {

    private static final long serialVersionUID = -4978046208743734170L;

    private Integer id;

    private Integer dicType;

    private String dicDesc;

    private Integer dicParentKey;

    private Integer dicKey;

    private String dicValue;

    private Integer dicSort;

}
