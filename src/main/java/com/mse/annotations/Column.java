package com.mse.annotations;

import java.lang.annotation.*;

/**
 * Maps a class field to a specific column index in a delimited file.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Column {

    /**
     * The zero-based position of the data in the file line.
     * @return the column index
     */
    int index();

    /**
     * Optional label for the column.
     * @return the column name
     */
    String name() default "";
}