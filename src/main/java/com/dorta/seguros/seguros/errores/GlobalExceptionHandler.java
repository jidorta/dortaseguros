package com.dorta.seguros.seguros.errores;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>>handleBadRequest(IllegalArgumentException ex){
        Map<String,Object>body = new HashMap<>();
        body.put("status", 400);
        body.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(body);
    }
}
