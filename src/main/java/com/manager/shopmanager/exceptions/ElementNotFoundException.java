package com.manager.shopmanager.exceptions;

public class ElementNotFoundException extends RuntimeException {

    private String message = "Requested element not found.";

    public ElementNotFoundException() {
        super();
    }

    public ElementNotFoundException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
