package com.mse.model;

import com.mse.annotations.*;

/**
 * Model class representing a security audit log entry.
 * Uses a semicolon delimiter and applies specific regex and range constraints
 * to audit data.
 */
@FileSource(delimiter = ";")
public class SecurityAudit {

    /**
     * The IP address associated with the audit event.
     */
    @Column(index = 0, name = "ip")
    @NotNull(message = "IP address is required")
    @Regex(
            pattern = "^\\d{1,3}(\\.\\d{1,3}){3}$",
            message = "Invalid IP format"
    )
    private String ip;

    /**
     * The severity level of the security event.
     */
    @Column(index = 1, name = "severity")
    @Range(min = 1, max = 5, message = "Severity must be between 1 and 5")
    private int severity;

    /**
     * Returns a string representation of the security audit entry.
     * @return formatted audit details
     */
    @Override
    public String toString() {
        return ip + " | " + severity;
    }
}