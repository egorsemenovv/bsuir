package org.example.exceptions;

public class NotEnoughMoneyException extends Throwable{
    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
