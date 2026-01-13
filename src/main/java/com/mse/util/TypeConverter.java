package com.mse.util;

import com.mse.parser.ParsingException;

import java.time.LocalDate;

public class TypeConverter {

    public static Object convert(String value, Class<?> targetType) {

        if (value == null || value.isBlank()) {
            return null;
        }

        try {
            if (targetType == String.class) {
                return value.trim();
            }
            if (targetType == int.class || targetType == Integer.class) {
                return Integer.parseInt(value.trim());
            }
            if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(value.trim());
            }
            if (targetType == boolean.class || targetType == Boolean.class) {
                return Boolean.parseBoolean(value.trim());
            }
            if (targetType == LocalDate.class) {
                return LocalDate.parse(value.trim());
            }
        } catch (Exception e) {
            throw new ParsingException(
                    "Type conversion failed for value '" + value +
                            "' to type " + targetType.getSimpleName(),
                    e
            );
        }

        throw new ParsingException(
                "Unsupported target type: " + targetType.getName()
        );
    }
}