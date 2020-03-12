package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨万浩
 * @description ResetPasswordDto类
 * @date 2020/3/10 19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetPasswordDto implements Serializable {

    private String oldPassword;

    private String newPassword;

    private String reNewPassword;

}
