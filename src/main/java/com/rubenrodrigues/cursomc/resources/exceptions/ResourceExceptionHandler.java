package com.rubenrodrigues.cursomc.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rubenrodrigues.cursomc.services.exceptions.DataIntegrityException;
import com.rubenrodrigues.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(se);
	}

	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
		ValidarionError ve = new ValidarionError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", Instant.now());
		for (FieldError fe : e.getBindingResult().getFieldErrors()) {
			ve.addError(fe.getField(), fe.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ve);
	}

}
