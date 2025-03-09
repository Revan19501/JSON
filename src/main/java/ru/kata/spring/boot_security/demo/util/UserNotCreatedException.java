package ru.kata.spring.boot_security.demo.util;

public class UserNotCreatedException extends RuntimeException {

    public UserNotCreatedException(String message) {
        super(message);
    }
}
