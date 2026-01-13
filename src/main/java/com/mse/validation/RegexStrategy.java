package com.mse.validation;

import com.mse.annotations.Regex;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.regex.Pattern;

public class RegexStrategy implements ValidationStrategy {

    @Override
    public Optional<String> validate(Object value, Annotation annotation) {
        if (value == null) return Optional.empty();

        Regex regex = (Regex) annotation;
        return Pattern.matches(regex.pattern(), value.toString())
                ? Optional.empty()
                : Optional.of(regex.message());
    }
}