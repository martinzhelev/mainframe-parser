package com.mse.parser;

import com.mse.annotations.Column;
import com.mse.annotations.FileSource;
import com.mse.util.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * The core engine of the framework responsible for converting flat files into Java objects.
 * It utilizes reflection to inspect class metadata and map file columns to object fields.
 */
public class GenericFileParser {

    private static final Logger log =
            LoggerFactory.getLogger(GenericFileParser.class);

    /**
     * Parses a delimited resource file into a list of strongly-typed objects.
     * This method reads the file line-by-line, skips blank lines, and handles malformed data
     * by logging an error and continuing to the next record.
     * * @param <T> The generic type of the class being parsed
     * @param resourceName The path to the file relative to the classpath root
     * @param clazz The model class containing @FileSource and @Column annotations
     * @return A list of successfully instantiated and populated objects
     * @throws ParsingException If the class is missing required annotations or the file is not found
     */
    public <T> List<T> parse(String resourceName, Class<T> clazz) {

        FileSource fileSource = clazz.getAnnotation(FileSource.class);
        if (fileSource == null) {
            throw new ParsingException(
                    "Class " + clazz.getSimpleName() + " missing @FileSource annotation"
            );
        }

        String delimiter = fileSource.delimiter();
        List<T> result = new ArrayList<>();

        log.info(
                "Starting parsing file '{}' into class {}",
                resourceName,
                clazz.getSimpleName()
        );

        try (InputStream is =
                     Thread.currentThread()
                             .getContextClassLoader()
                             .getResourceAsStream(resourceName)) {

            if (is == null) {
                throw new ParsingException(
                        "Resource not found on classpath: " + resourceName
                );
            }

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(is));

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                // Uses Pattern.quote to handle special regex characters like the pipe symbol
                String[] tokens = line.split(java.util.regex.Pattern.quote(delimiter));

                try {
                    T instance =
                            clazz.getDeclaredConstructor().newInstance();

                    for (Field field : clazz.getDeclaredFields()) {
                        Column column =
                                field.getAnnotation(Column.class);

                        if (column == null) {
                            continue;
                        }

                        int index = column.index();
                        if (index >= tokens.length) {
                            continue;
                        }

                        String rawValue = tokens[index];

                        field.setAccessible(true);

                        // Delegates type conversion to the TypeConverter utility
                        Object convertedValue =
                                TypeConverter.convert(
                                        rawValue,
                                        field.getType()
                                );

                        field.set(instance, convertedValue);
                    }

                    result.add(instance);

                } catch (Exception e) {
                    // Logs malformed lines and continues parsing to ensure robustness
                    log.error(
                            "Skipping malformed line {} in {}: {}",
                            lineNumber,
                            resourceName,
                            e.getMessage()
                    );
                }
            }

        } catch (Exception e) {
            throw new ParsingException(
                    "Failed to parse file " + resourceName,
                    e
            );
        }

        return result;
    }
}