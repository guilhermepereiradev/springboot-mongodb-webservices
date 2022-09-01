package com.oxd.springbootmongodb.resource.exceptions;

import com.oxd.springbootmongodb.services.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.Serial;
import java.io.Serializable;

@ControllerAdvice
public class ResourceExceptionHandler implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> ObjectNotFound(ObjectNotFoundException e, HttpServletRequest request){
        String error = "Objeto não encontrado";
        HttpStatus status = HttpStatus.NOT_FOUND;

        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());

        return ResponseEntity.status(status).body(err);
    }
}
