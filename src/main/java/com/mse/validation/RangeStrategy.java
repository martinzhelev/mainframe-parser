package com.mse.validation;

import com.mse.annotations.Range;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class RangeStrategy implements ValidationStrategy {

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
