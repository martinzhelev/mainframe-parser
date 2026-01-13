package com.mse.model;

import com.mse.annotations.*;

/**
 * Model class representing a customer entity.
 * Defined as a pure domain model with zero logic, using annotations to declare
 * file mapping and validation rules.
 */
@FileSource(delimiter = ",")
public class Customer {

    /**
     * The full name of the customer.
     */
    @Column(index = 0, name = "name")
    @NotNull(message = "Name is required")
    private String name;

    /**
     * The customer's contact email address.
     */
    @Column(index = 1, name = "email")
    @Regex(
            pattern = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Invalid email format"
    )
    private String email;

    /**
     * The age of the customer.
     */
    @Column(index = 2, name = "age")
    @Range(min = 18, max = 99, message = "Age must be between 18 and 99")
    private int age;

    /**
     * Returns a string representation of the customer data.
     * @return formatted customer details
     */
    @Override
    public String toString() {
        return name + " | " + email + " | " + age;
    }
}