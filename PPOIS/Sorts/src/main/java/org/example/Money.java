package org.example;

import java.util.Objects;

public class Money implements Comparable<Money>, Numerable{
    private final int value;

    public Money(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Money o) {
        return Integer.compare(value, o.getValue());
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return value == money.value;
    }
}
