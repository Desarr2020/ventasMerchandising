package com.informatorio.ventasMerchandising.exceptions;

public class ResourceNotFound extends RuntimeException {

    public ResourceNotFound(String message) {
        super(message);
    }
}