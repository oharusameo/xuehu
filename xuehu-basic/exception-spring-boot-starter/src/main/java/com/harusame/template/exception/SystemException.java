package com.harusame.template.exception;

public class SystemException extends RuntimeException {
    private Integer statusCode;

    public Integer getStatusCode() {
        return statusCode;
    }

    public SystemException(Integer statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
    }

    public SystemException(String message, Exception exception) {
        super(message, exception);
    }

    public SystemException(String message) {
        super(message);
    }
}
