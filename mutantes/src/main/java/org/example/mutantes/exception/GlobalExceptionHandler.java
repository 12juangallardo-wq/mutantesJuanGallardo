package org.example.mutantes.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // =====================================================
    // ❌ JSON mal formateado → 400 BAD REQUEST
    // =====================================================
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "JSON mal formado o inválido.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // =====================================================
    // ❌ Validaciones propias del Service → 400 BAD REQUEST
    // =====================================================
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgument(IllegalArgumentException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", ex.getMessage()); // Mensaje claro del service

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // =====================================================
    // ❌ Errores de validación automática (si usaras @Valid)
    // =====================================================
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Solicitud inválida.");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // =====================================================
    // ❌ Cualquier error inesperado → 500
    // =====================================================
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "Error interno del servidor.");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}