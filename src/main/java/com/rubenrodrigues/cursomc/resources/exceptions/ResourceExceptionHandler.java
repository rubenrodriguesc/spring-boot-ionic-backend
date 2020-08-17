package com.rubenrodrigues.cursomc.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.rubenrodrigues.cursomc.services.exceptions.AuthorizationException;
import com.rubenrodrigues.cursomc.services.exceptions.DataIntegrityException;
import com.rubenrodrigues.cursomc.services.exceptions.FileException;
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
	
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(se);
	}
	
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(AuthorizationException e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
	}
	
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
		HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
		StandardError se = new StandardError(code.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(code).body(se);
	}
	
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
	}
	
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
		StandardError se = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), Instant.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(se);
	}

}
