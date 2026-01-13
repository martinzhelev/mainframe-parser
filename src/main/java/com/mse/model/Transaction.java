package com.mse.model;

import com.mse.annotations.*;
import java.time.LocalDate;

/**
 * Model class representing a financial transaction.
 * This class acts as a domain model that defines data mapping and business constraints
 * through annotations.
 */
@FileSource(delimiter = "|")
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Column(index = 0, name = "id")
    @NotNull(message = "Transaction ID is required")
    private String id;

    /**
     * The monetary amount of the transaction.
     * Uses Double to allow for null values during the parsing phase.
     */
    @Column(index = 1, name = "amount")
    @NotNull(message = "Amount is required")
    @Range(min = 0, max = 10000000, message = "Amount must be between 0 and 10,000,000")
    private Double amount;

    /**
     * The date the transaction occurred.
     * Parsed from an ISO-8601 formatted string (YYYY-MM-DD).
     */
    @Column(index = 2, name = "date")
    @NotNull(message = "Transaction date is required")
    private LocalDate date;

    /**
     * Returns a string representation of the transaction record.
     * @return formatted transaction details
     */
    @Override
    public String toString() {
        return id + " | " + amount + " | " + date;
    }
}