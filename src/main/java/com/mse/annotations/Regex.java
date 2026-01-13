package com.mse.annotations;

import java.lang.annotation.*;

/**
 * Validation annotation to enforce string pattern matching via regular expressions.
 * Used by the {@code Validator} to ensure data integrity during the post-parsing phase.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Regex {

    /**
     * The regular expression pattern that the field value must match.
     * @return the regex pattern string
     */
    String pattern();

    /**
     * Error message returned if the field value fails the regex match.
     * @return the validation message
     */
    String message() default "Value does not match pattern";
}