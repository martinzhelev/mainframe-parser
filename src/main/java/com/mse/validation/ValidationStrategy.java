package com.mse.validation;

import java.lang.annotation.Annotation;
import java.util.Optional;

public interface ValidationStrategy {
    Optional<String> validate(Object value, Annotation annotation);
}