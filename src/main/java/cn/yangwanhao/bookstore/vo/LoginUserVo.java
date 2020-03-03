package cn.yangwanhao.bookstore.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 杨万浩
 * @description LoginUserVo类
 * @date 2020/2/27 16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginUserVo {

    private Long id;

    private String loginname;

    private String password;

    private String phone;

    private String email;

    private Integer pwdErrorCount;

    private Integer isLocked;

    private Integer userType;

}
