package com.mse.annotations;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Regex {
    String pattern();
    String message() default "Value does not match pattern";
}
