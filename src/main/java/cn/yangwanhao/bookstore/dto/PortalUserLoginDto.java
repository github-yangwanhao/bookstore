package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 杨万浩
 * @description UserLoginDto类
 * @date 2020/3/9 09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PortalUserLoginDto implements Serializable {

    private String username;

    private String password;

}
