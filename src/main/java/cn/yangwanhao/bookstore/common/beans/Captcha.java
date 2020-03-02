package cn.yangwanhao.bookstore.common.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * redis验证码bean类
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/10/22 14:10
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Captcha implements Serializable {

    private static final long serialVersionUID = -2699924776630566564L;
    /**
     * 验证码
     */
    private String code;
    /**
     * 剩余验证次数
     */
    private Integer chances;
}