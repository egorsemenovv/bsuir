package org.example.enums;

import java.math.BigDecimal;

public enum Employee {
    PLUMBER(new BigDecimal("100.00")),
    ELECTRICIAN(new BigDecimal("150.00"));

    private final java.math.BigDecimal payment;
    Employee(java.math.BigDecimal payment) {
        this.payment=payment;
    }
    /**
     * @return payment for worker
     */
    public java.math.BigDecimal getPayment() {
        return payment;
    }
}
