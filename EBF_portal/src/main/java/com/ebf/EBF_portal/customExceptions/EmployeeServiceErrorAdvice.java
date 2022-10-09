package com.ebf.EBF_portal.customExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;


import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
@Slf4j
public class EmployeeServiceErrorAdvice {

	Logger log = LoggerFactory.getLogger(EmployeeServiceErrorAdvice.class);
	
	@ExceptionHandler({RuntimeException.class})
    public ResponseEntity<String> handleRunTimeException(RuntimeException e) {
        return error(INTERNAL_SERVER_ERROR, e);
    }

    @ExceptionHandler({EmployeeNotFound.class})
    public ResponseEntity<String> handleEmpNotFoundException(EmployeeNotFound e) {
        return error(NOT_FOUND, e);
    }

    @ExceptionHandler({EmployeeServiceException.class})
    public ResponseEntity<String> handleEmplServiceException(EmployeeServiceException e){
        return error(INTERNAL_SERVER_ERROR, e);
    }

    private ResponseEntity<String> error(HttpStatus status, Exception e) {
        log.error("Exception : ", e);
        return ResponseEntity.status(status).body(e.getMessage());
    }
}