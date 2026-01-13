package com.mse.util;

import com.mse.parser.ParsingException;

import java.time.LocalDate;

/**
 * Utility class responsible for converting raw string data from files into strongly-typed Java objects.
 * This component acts as a bridge between the text-based source and the typed model fields.
 */
public class TypeConverter {

    /**
     * Converts a string value to the specified target Java type.
     * Supports String, Integer, Double, Boolean, and LocalDate (ISO format).
     * * @param value The raw string value to convert.
     * @param targetType The class type the value should be converted into.
     * @return The converted object, or null if the input value is blank.
     * @throws ParsingException If the conversion fails or the target type is not supported.
     */
    public static Object convert(String value, Class<?> targetType) {

        // Returns null for empty data to allow the Validator to handle @NotNull constraints later.
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
                // Expects ISO-8601 format: YYYY-MM-DD.
                return LocalDate.parse(value.trim());
            }
        } catch (Exception e) {
            // Failure here results in a skipped line in the GenericFileParser.
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