package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.annotation.NotBlank;
import cn.yangwanhao.bookstore.common.constant.GlobalValidantConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/11/29 19:35
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdvertisementsDto implements Serializable {

    private static final long serialVersionUID = 7961238639523824582L;

    public Integer id;

    @NotBlank(message = GlobalValidantConstant.I1004)
    private String title;

    @NotBlank(message = GlobalValidantConstant.I1005)
    private String url;

    @NotBlank(message = GlobalValidantConstant.I1006)
    private String image;

    private Integer category;

    private Integer sort;

}
