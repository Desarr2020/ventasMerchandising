package com.informatorio.ventasMerchandising.exceptions;

public class CarritoErrorMessage {
    private String message;
    private String exception;
    private String path;

    public CarritoErrorMessage(Exception exception, String path) {
        this.message = exception.getMessage();
        this.exception = exception.getClass().getSimpleName();
        this.path = path;
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "CarritoErrorMessage{" +
                "message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
