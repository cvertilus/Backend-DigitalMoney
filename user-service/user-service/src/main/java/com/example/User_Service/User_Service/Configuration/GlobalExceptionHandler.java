package com.example.User_Service.User_Service.Configuration;

import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FeignException.NotFound.class)
    public ResponseEntity<String> handleNotFound(FeignException.NotFound e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe");
    }

    @ExceptionHandler(FeignException.BadRequest.class)
    public ResponseEntity<String> handleBadRequest(FeignException.BadRequest e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Solicitud Incorrecta");
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<String> handleFeignException(FeignException e) {
        if (e.status() == 500) {
            String message = e.getMessage();
            // Puedes parsear aquí si es JSON, o buscar el texto que te interese
            if (message.contains("duplicate key value")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Conflicto: el recurso ya existe");
            }
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}
