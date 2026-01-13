package com.mse.validation;

import com.mse.annotations.Range;

import java.lang.annotation.Annotation;
import java.util.Optional;

/**
 * Concrete validation strategy for enforcing numeric range constraints.
 * Validates that a numeric value falls between specified minimum and maximum bounds.
 */
public class RangeStrategy implements ValidationStrategy {

    /**
     * Checks if the numeric value is within the allowed range defined by the annotation.
     * If the value is null, validation is skipped (handled by NotNullStrategy if present).
     * @param value The numeric object to validate.
     * @param annotation The @Range annotation containing min, max, and message.
     * @return An Optional error message if the value is out of bounds, otherwise empty.
     */
    @Override
    public Optional<String> validate(Object value, Annotation annotation) {
        if (value == null) return Optional.empty();

        Range range = (Range) annotation;
        double number = ((Number) value).doubleValue();

        return (number < range.min() || number > range.max())
                ? Optional.of(range.message())
                : Optional.empty();
    }
}