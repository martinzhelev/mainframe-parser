package com.mse.model;

import com.mse.annotations.*;

@FileSource(delimiter = ";")
public class SecurityAudit {

    @Column(index = 0, name = "ip")
    @NotNull(message = "IP address is required")
    @Regex(
            pattern = "^\\d{1,3}(\\.\\d{1,3}){3}$",
            message = "Invalid IP format"
    )
    private String ip;

    @Column(index = 1, name = "severity")
    @Range(min = 1, max = 5, message = "Severity must be between 1 and 5")
    private int severity;

    @Override
    public String toString() {
        return ip + " | " + severity;
    }
}