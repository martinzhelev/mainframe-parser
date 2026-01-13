package com.mse.validation;

import com.mse.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Orchestrates the validation process for a list of model objects.
 * It maps specific annotations to their corresponding {@code ValidationStrategy}
 * and aggregates any constraint violations.
 */
public class Validator {

    private static final Logger log =
            LoggerFactory.getLogger(Validator.class);

    /**
     * Registry of available validation strategies mapped by annotation type.
     */
    private final Map<Class<? extends Annotation>, ValidationStrategy> strategies =
            Map.of(
                    NotNull.class, new NotNullStrategy(),
                    Regex.class, new RegexStrategy(),
                    Range.class, new RangeStrategy()
            );

    /**
     * Inspects objects via reflection to apply validation rules defined in annotations.
     * * @param <T> The type of objects being validated.
     * @param objects The list of instances to check.
     * @return A map where the key is the invalid object and the value is a set of error messages.
     */
    public <T> Map<T, Set<String>> validate(List<T> objects) {
        log.info("Starting validation of {} objects", objects.size());

        Map<T, Set<String>> errors = new HashMap<>();

        for (T obj : objects) {
            Set<String> messages = new HashSet<>();

            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true);

                for (Annotation annotation : field.getAnnotations()) {
                    ValidationStrategy strategy =
                            strategies.get(annotation.annotationType());

                    if (strategy == null) continue;

                    try {
                        Object value = field.get(obj);
                        // Executes the strategy and collects any error messages returned.
                        strategy.validate(value, annotation)
                                .ifPresent(messages::add);
                    } catch (IllegalAccessException e) {
                        log.error("Reflection error during validation", e);
                        throw new RuntimeException(e);
                    }
                }
            }

            if (!messages.isEmpty()) {
                errors.put(obj, messages);
            }
        }

        log.info("Validation completed. {} objects with errors.",
                errors.size());

        return errors;
    }
}