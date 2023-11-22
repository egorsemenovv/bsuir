package org.example;

public class NotEnoughMoneyException extends Throwable{
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
