package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨万浩
 * @description AddUserDto类
 * @date 2020/2/27 10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDto implements Serializable {

    private String loginname;

    private String password;

    private String realname;

    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String phone;

    private String email;

    private Integer userType;

}

