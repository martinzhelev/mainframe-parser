package com.mse.annotations;

import java.lang.annotation.*;

/**
 * Validation annotation used to mark a field as mandatory.
 * Used by the {@code Validator} after parsing is complete.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface NotNull {

    /**
     * Error message returned if the field is null.
     * @return the validation message
     */
    String message() default "Field must not be null";
}