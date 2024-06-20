package com.pottery.service.products.configurations;

import com.pottery.service.products.dtos.ErrorDto;
import com.pottery.service.products.exceptions.AppException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.PathElementException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDto> handleAppException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDto(ex.getMessage()));
    }

    @ExceptionHandler(TypeMismatchException.class)
    @ResponseBody
    public ResponseEntity<String> handleIOException(TypeMismatchException e, HttpServletRequest request) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Invalid parameter: " + e.getValue()
                        + " for " + e.getPropertyName()
                        + " in " + request.getRequestURI());
    }

    @ExceptionHandler(PathElementException.class)
    @ResponseBody
    public ResponseEntity<String> handleIOException(PathElementException e) {
        log.error("Invalid parameter in filter request : {}", e.getMessage());
        String message = e.getMessage().substring(0, e.getMessage().indexOf("of '"));
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(message);
    }

}
