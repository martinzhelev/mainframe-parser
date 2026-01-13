package com.mse.validation;

import com.mse.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Concrete validation strategy for checking null values.
 * Part of the Strategy pattern implementation for field-level validation.
 */
public class NotNullStrategy implements ValidationStrategy {

    /**
     * Validates that the provided value is not null.
     * @param value The field value to check.
     * @param annotation The @NotNull annotation instance containing the error message.
     * @return An Optional containing the error message if null, otherwise empty.
     */
    @Override
    public Optional<String> validate(Object value, Annotation annotation) {
        NotNull notNull = (NotNull) annotation;
        return value == null
                ? Optional.of(notNull.message())
                : Optional.empty();
    }
}