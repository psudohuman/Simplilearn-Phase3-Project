package com.simplilearn.webservice.exceptions.handler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.simplilearn.webservice.exceptions.ProductNotFoundException;

@ControllerAdvice		//supposed to give a ExceptionResponse
public class GlobalExceptionHandler {
	
	ExceptionResponse response;
	
	@ExceptionHandler(value=ProductNotFoundException.class)
	public ResponseEntity<ExceptionResponse> notFoundException(ProductNotFoundException exception){
		response = new ExceptionResponse(exception.getMessage(),new Date(), HttpStatus.NOT_FOUND.name(), exception.getClass().getSimpleName());
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
}
