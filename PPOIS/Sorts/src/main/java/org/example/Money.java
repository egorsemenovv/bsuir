package org.example;

import java.util.Objects;

public class Money extends Number implements Comparable<Money>{
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Money o) {
        return Integer.compare(value, o.intValue());
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return (long) value;
    }

    @Override
    public float floatValue() {
        return (float) value;
    }

    @Override
    public double doubleValue() {
        return (double) value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }
}
