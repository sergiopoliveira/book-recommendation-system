package com.book.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.book.exceptions.ErrorResponse;
import com.book.exceptions.InvalidParameterException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// add an exception handler for CustomerNotFoundException
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(InvalidParameterException e) {
			
			//create CustomerErrorResponse
			ErrorResponse error = new ErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												"Invalid Parameter",
												e.getMessage(),
												System.currentTimeMillis());

			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		}
		
		// http error client
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(HttpClientErrorException e) {
			
			//create CustomerErrorResponse
			ErrorResponse error = new ErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												"Invalid Parameter",
												"Currency does not exist",
												System.currentTimeMillis());

			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
		
		
		// add another exception handler ... to catch any exception (catch all)
		@ExceptionHandler
		public ResponseEntity<ErrorResponse> handleException(Exception e) {
			
			//create CustomerErrorResponse
			ErrorResponse error = new ErrorResponse(
												HttpStatus.BAD_REQUEST.value(),
												e.getMessage(),
												"Error ocurred",
												System.currentTimeMillis());

			//return ResponseEntity
			return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}