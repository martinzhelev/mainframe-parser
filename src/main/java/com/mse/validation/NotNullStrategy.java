package com.mse.validation;

import com.mse.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.Optional;

public class NotNullStrategy implements ValidationStrategy {

    @Override
    public Optional<String> validate(Object value, Annotation annotation) {
        NotNull notNull = (NotNull) annotation;
        return value == null
                ? Optional.of(notNull.message())
                : Optional.empty();
    }
}
