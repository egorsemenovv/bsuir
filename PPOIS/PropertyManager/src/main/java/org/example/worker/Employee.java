package org.example.worker;

import java.math.BigDecimal;

public enum Employee {
    PLUMBER("PLUMBER", new BigDecimal("100.00")),
    ELECTRICIAN("ELECTRICIAN", new BigDecimal("150.00"));
    private final String type;
    private final java.math.BigDecimal payment;
    Employee(String type, java.math.BigDecimal payment) {
        this.type=type;
        this.payment=payment;
    }
    public String getType() {
        return type;
    }
    public java.math.BigDecimal getPayment() {
        return payment;
    }
}
