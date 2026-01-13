package com.mse.annotations;

import java.lang.annotation.*;

/**
 * Validation annotation to enforce numeric bounds on a field.
 * Checked by the {@code Validator} during the post-parsing phase.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Range {

    /**
     * The minimum inclusive value allowed.
     * @return the minimum bound
     */
    double min();

    /**
     * The maximum inclusive value allowed.
     * @return the maximum bound
     */
    double max();

    /**
     * Error message returned if the value is outside the specified bounds.
     * @return the validation message
     */
    String message() default "Value out of range";
}