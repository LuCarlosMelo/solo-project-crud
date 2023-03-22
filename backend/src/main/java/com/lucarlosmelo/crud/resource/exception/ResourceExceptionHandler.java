package com.lucarlosmelo.crud.resource.exception;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lucarlosmelo.crud.services.exception.DataBaseException;
import com.lucarlosmelo.crud.services.exception.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error ="Resource not found";
		
		StandardError err = standardError(status, error, e, request);
		
		return ResponseEntity.status(status).body(err);
	}
	
	//Esta Exception handler ainda não é nessário, mas vou deixar aqui
	
	/*
	 * @ExceptionHandler(DataBaseException.class) public
	 * ResponseEntity<StandardError> dataBase(DataBaseException e,
	 * HttpServletRequest request) { HttpStatus status = HttpStatus.BAD_REQUEST;
	 * String error = "Database excpetion";
	 * 
	 * StandardError err = standardError(status, error, e, request);
	 * 
	 * return ResponseEntity.status(status).body(err); }
	 */
	
	private StandardError standardError(HttpStatus status, String error, RuntimeException e, HttpServletRequest request ) {
		StandardError err = new StandardError();
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError(error);
		err.setMessage(e.getMessage());
		err.setPath(request.getRequestURI());		
		return err;
	}
}










