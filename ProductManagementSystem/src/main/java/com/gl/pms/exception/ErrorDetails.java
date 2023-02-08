package com.gl.pms.exception;

import java.io.Serializable;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	private String errorCode;
	private String errorDetails;

	public ErrorDetails() {
		super();
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	@Override
	public String toString() {
		return "ErrorDetails [errorCode=" + errorCode + ", errorDetails=" + errorDetails + "]";
	}

}


