package de.telran.ticketapp.exception;

import javax.management.RuntimeErrorException;

public class NoUniqueLocalUserEmailException extends RuntimeException {
    public NoUniqueLocalUserEmailException(String message) {
        super(message);
    }
}
