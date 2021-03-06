package com.example.consumerBank.java.exception;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.consumerBank.java.exceptions.AccountNotExistException;

// TODO: Auto-generated Javadoc
/**
 * The Class GlobalExceptionHandler.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(MethodArgumentNotValidException ex) {
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTimel(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Invalid data");

		for (FieldError fieldError : errors) {
			validationErrorResponse.getErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ValidationErrorResponse> handleException(ConstraintViolationException ex) {

		ValidationErrorResponse validationErrorResponse = new ValidationErrorResponse();
		validationErrorResponse.setDateTimel(LocalDateTime.now());
		validationErrorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		validationErrorResponse.setMessage("Invalid data");

		ex.getConstraintViolations().forEach(error -> {
			validationErrorResponse.getErrors().put("field", error.getMessage());
		});

		return new ResponseEntity<ValidationErrorResponse>(validationErrorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(CustomerNotFoundException ex) {

		ErrorResponse errorResponse = new ValidationErrorResponse();
		errorResponse.setDateTimel(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage("Invalid data");

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handle exception.
	 *
	 * @param ex the ex
	 * @return the response entity
	 */
	@ExceptionHandler(AccountNotExistException.class)
	public ResponseEntity<ErrorResponse> handleException(AccountNotExistException ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setDateTimel(LocalDateTime.now());
		errorResponse.setStatuscode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

}
