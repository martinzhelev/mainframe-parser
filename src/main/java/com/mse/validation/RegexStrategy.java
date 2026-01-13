package com.mse.validation;

import com.mse.annotations.Regex;

import java.lang.annotation.Annotation;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * Concrete validation strategy for pattern matching using regular expressions.
 * Ensures that a field's string representation conforms to a specific format.
 */
public class RegexStrategy implements ValidationStrategy {

    /**
     * Validates a value against the regex pattern defined in the annotation.
     * If the value is null, the validation is skipped.
     * @param value The object whose string representation is to be validated.
     * @param annotation The @Regex annotation containing the pattern and error message.
     * @return An Optional error message if the pattern does not match, otherwise empty.
     */
    @Override
    public Optional<String> validate(Object value, Annotation annotation) {
        if (value == null) return Optional.empty();

        Regex regex = (Regex) annotation;
        return Pattern.matches(regex.pattern(), value.toString())
                ? Optional.empty()
                : Optional.of(regex.message());
    }
}