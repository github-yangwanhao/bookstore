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
 * @date 2019/12/26 17:25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementsListVo implements Serializable {

    private static final long serialVersionUID = 1883693574535395109L;

    private Integer id;

    private String title;

    private String url;

    private String image;

    private Integer category;

    private Integer sort;

}
