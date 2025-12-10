package com.TechLab.demo.controller;

import com.TechLab.demo.exception.NotFoundException;
import com.TechLab.demo.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductExceptionHandleController {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<String> handleNotFoundException(NotFoundException e){
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
  }

  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<String> handlerValidationException(Exception e){
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handlerUnKnowException(Exception e){
    e.printStackTrace();
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
  }
}
