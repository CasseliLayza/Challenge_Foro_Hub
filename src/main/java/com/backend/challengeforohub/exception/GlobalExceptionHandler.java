package com.backend.challengeforohub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarMethodArgumentNotValidException(MethodArgumentNotValidException methodArgumentNotValidException) {
        Map<String, String> mensaje = new HashMap<>();
        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(e -> {
            String nombreCampo = ((FieldError) e).getField();
            String mensajeError = e.getDefaultMessage();
            mensaje.put(nombreCampo, mensajeError);
        });

        return mensaje;
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResouceNotFoundException(ResourceNotFoundException resouceNotFoundException) {

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado: " + resouceNotFoundException.getMessage());

        return mensaje;
    }

    @ExceptionHandler({DuplicateDniException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> manejarDuplicateDniException(DuplicateDniException duplicateDniException ) {

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no registrado: " + duplicateDniException .getMessage());

        return mensaje;
    }

    @ExceptionHandler({DuplicateRolException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> manejarDuplicateRolException(DuplicateRolException duplicateRolException ) {

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no registrado: " + duplicateRolException  .getMessage());

        return mensaje;
    }

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Map<String, String> manejarSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException violationException ) {

        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no registrado: " + violationException  .getMessage());

        return mensaje;
    }

}


