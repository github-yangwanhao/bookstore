package cn.yangwanhao.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 杨万浩
 * @description UserInfoDto类
 * @date 2020/3/12 09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto implements Serializable {

    private Long userId;

    private String realname;

    private String phone;

    private String email;

    private Integer sex;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

}
