package cn.yangwanhao.bookstore.common.annotation.validator;


import cn.yangwanhao.bookstore.common.annotation.NotBlank;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 11:07
 */

public class NotBlankValidator implements ConstraintValidator<NotBlank, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || "".equals(s) || s.length() == 0) {
            return false;
        }
        return true;
    }
}
