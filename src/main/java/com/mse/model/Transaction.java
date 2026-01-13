package com.mse.model;

import com.mse.annotations.*;
import java.time.LocalDate;

@FileSource(delimiter = "|")
public class Transaction {

    @Column(index = 0, name = "id")
    @NotNull(message = "Transaction ID is required")
    private String id;

    @Column(index = 1, name = "amount")
    @NotNull(message = "Amount is required")
    @Range(min = 0, max = 10000000, message = "Amount must be between 0 and 10,000,000")
    private Double amount;

    @Column(index = 2, name = "date")
    @NotNull(message = "Transaction date is required")
    private LocalDate date;

    @Override
    public String toString() {
        return id + " | " + amount + " | " + date;
    }
}