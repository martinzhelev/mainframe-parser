package com.mse.annotations;

import java.lang.annotation.*;

/**
 * Defines the file-level configuration for a model class.
 * This annotation tells the parser which character separates the data fields in the text file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface FileSource {

    /**
     * The character or string used to split lines into individual columns.
     * @return the delimiter string (e.g., "|" or ",")
     */
    String delimiter();
}