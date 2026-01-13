package com.mse.model;

import com.mse.annotations.*;

@FileSource(delimiter = ",")
public class Customer {

    @Column(index = 0, name = "name")
    @NotNull(message = "Name is required")
    private String name;

    @Column(index = 1, name = "email")
    @Regex(
            pattern = "^[A-Za-z0-9+_.-]+@(.+)$",
            message = "Invalid email format"
    )
    private String email;

    @Column(index = 2, name = "age")
    @Range(min = 18, max = 99, message = "Age must be between 18 and 99")
    private int age;

    @Override
    public String toString() {
        return name + " | " + email + " | " + age;
    }
}