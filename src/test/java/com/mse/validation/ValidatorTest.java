package com.mse.validation;

import com.mse.model.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    void validate_shouldDetectInvalidFields() {
        Customer c1 = new Customer();
        Customer c2 = new Customer();

        // invalid
        setField(c1, "name", null);
        setField(c1, "email", "bad-email");
        setField(c1, "age", 10);

        // valid
        setField(c2, "name", "John");
        setField(c2, "email", "john@gmail.com");
        setField(c2, "age", 30);

        Map<Customer, Set<String>> result =
                validator.validate(List.of(c1, c2));

        assertEquals(1, result.size());
        assertTrue(result.get(c1).size() >= 2);
    }

    @Test
    void validate_validObject_shouldHaveNoErrors() {
        Customer customer = new Customer();
        setField(customer, "name", "Anna");
        setField(customer, "email", "anna@gmail.com");
        setField(customer, "age", 25);

        Map<Customer, Set<String>> result =
                validator.validate(List.of(customer));

        assertTrue(result.isEmpty());
    }

    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}