package com.mse.validation;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Interface defining a strategy for field-level validation.
 * Implementations provide specific logic for checking constraints like nullability,
 * numeric ranges, or pattern matching.
 */
public interface ValidationStrategy {

    /**
     * Executes the validation logic for a specific field value and annotation.
     * @param value The object value to be validated.
     * @param annotation The specific constraint annotation being checked.
     * @return An Optional containing an error message if validation fails, or empty if successful.
     */
    Optional<String> validate(Object value, Annotation annotation);
}