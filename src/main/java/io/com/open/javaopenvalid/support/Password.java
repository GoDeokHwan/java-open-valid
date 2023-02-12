package io.com.open.javaopenvalid.support;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordValidator.class)
@Documented
public @interface Password {
    String message() default "{javax.validation.constraints.Password.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 대문자 필수 포함 여부
     * */
    boolean capitalLetter() default true;

    /**
     * 소문자 필수 포함 여부
     * */
    boolean smallLetter() default true;

    /**
     * 특수 문자 필수 포함 여부
     * */
    boolean specialSymbol() default true;

    /**
     * 숫자 포함여부
     * */
    boolean number() default true;

    @Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        Password[] value();
    }
}
