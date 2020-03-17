package com.rubenrodrigues.cursomc.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardError implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String msg;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant instant;

	public StandardError(Integer status, String msg, Instant instant) {
		super();
		this.status = status;
		this.msg = msg;
		this.instant = instant;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Instant getInstant() {
		return instant;
	}

	public void setInstant(Instant instant) {
		this.instant = instant;
	}

}
