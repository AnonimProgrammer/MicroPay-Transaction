package com.docker.mstransaction.exception;

public class InvalidTransactionEventException extends RuntimeException {
    public InvalidTransactionEventException(String message) {
        super(message);
    }

    public InvalidTransactionEventException(String message, Throwable cause) {
        super(message, cause);
    }
}
