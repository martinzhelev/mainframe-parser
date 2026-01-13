package com.mse.parser;

import com.mse.model.Customer;
import com.mse.model.Transaction;
import com.mse.model.SecurityAudit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericFileParserTest {

    private final GenericFileParser parser = new GenericFileParser();

    @Test
    void parseCustomers_shouldParseValidLines() {
        // REMOVED: src/test/resources/
        List<Customer> customers =
                parser.parse("test-customers.txt", Customer.class);

        assertFalse(customers.isEmpty());
        assertEquals(5, customers.size());
    }

    @Test
    void parseTransactions_shouldSkipMalformedLines() {
        // REMOVED: src/test/resources/
        List<Transaction> transactions =
                parser.parse("test-transactions.txt", Transaction.class);

        assertEquals(1, transactions.size());
    }

    @Test
    void parseAudits_shouldHandleNullAndInvalidData() {
        // REMOVED: src/test/resources/
        List<SecurityAudit> audits =
                parser.parse("test-audits.txt", SecurityAudit.class);

        assertEquals(3, audits.size());
    }

    @Test
    void parse_nonExistingFile_shouldThrowException() {
        assertThrows(Exception.class, () ->
                parser.parse("missing-file.txt", Customer.class)
        );
    }
}