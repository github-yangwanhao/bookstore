package cn.yangwanhao.bookstore.dto;

import cn.yangwanhao.bookstore.common.annotation.NotBlank;
import cn.yangwanhao.bookstore.common.constant.GlobalValidantConstant;
import cn.yangwanhao.bookstore.common.constant.ValidatePattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 11:46
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressDto implements Serializable {

    private static final long serialVersionUID = -9048360621381234087L;

    private Long id;

    @NotBlank(message = GlobalValidantConstant.U1003)
    private String receiverName;

    @NotBlank(message = GlobalValidantConstant.U1001)
    @Pattern(regexp = ValidatePattern.MOBILE_PHONE, message = GlobalValidantConstant.U2001)
    private String receiverPhone;

    @NotBlank(message = GlobalValidantConstant.U1002)
    private String receiverAddress;

    private Integer isDefault = 0;
}
