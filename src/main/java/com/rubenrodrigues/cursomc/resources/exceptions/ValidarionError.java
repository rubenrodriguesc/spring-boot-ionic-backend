package com.rubenrodrigues.cursomc.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidarionError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> errors = new ArrayList<>();
	
	public ValidarionError(Integer status, String msg, Instant instant) {
		super(status, msg, instant);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}
	
	public void addError(String fieldName, String message) {
		errors.add(new FieldMessage(fieldName, message));
	}

}
