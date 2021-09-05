package com.informatorio.ventasMerchandising.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ApiHandlerExceptions {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {ResourceNotFound.class})
    @ResponseBody
    public CarritoErrorMessage carritoNotFound(RuntimeException ex, HttpServletRequest request) {
        return new CarritoErrorMessage(ex, request.getRequestURI());
    }
}