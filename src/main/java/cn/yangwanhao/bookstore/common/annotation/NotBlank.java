package cn.yangwanhao.bookstore.common.annotation;


import cn.yangwanhao.bookstore.common.annotation.validator.NotBlankValidator;
import cn.yangwanhao.bookstore.common.enums.ErrorCodeEnum;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * description
 *
 * @author 杨万浩
 * @version 1.0.0
 * @date 2019/12/7 11:06
 */

@Documented
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NotBlankValidator.class })
public @interface NotBlank {

    String message() default "参数不允许为空";

    ErrorCodeEnum msg() default ErrorCodeEnum.G500102;
}
