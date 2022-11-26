package com.manager.shopmanager.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorMessage {
    private String method;
    private int status;
    private String message;
    private List<ValidationError> errors;

    private String path;

    public ErrorMessage(int status, String message, String stackTrace, List<ValidationError> errors, String path) {
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.path = path;
    }

    public ErrorMessage(int status, String message, String path, String method) {
        this.status = status;
        this.message = message;
        this.path = path;
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ValidationError> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationError> errors) {
        this.errors = errors;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private static class ValidationError {
        private String field;
        private String message;

        public ValidationError(String field, String message) {
            this.field = field;
            this.message = message;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }

    public void addValidationError(String field, String message) {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        errors.add(new ValidationError(field, message));
    }

}
