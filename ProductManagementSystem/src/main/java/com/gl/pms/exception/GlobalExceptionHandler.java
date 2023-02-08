package com.gl.pms.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.gl.pms.dto.Response;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	Logger logger = LoggerFactory.getLogger(ResponseEntityExceptionHandler.class);

	@Autowired
	private Environment environment;

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> ResponseEntityExceptionHandler(Exception ex, WebRequest request)
			throws Exception {
		logger.info("Inside ResponseEntityExceptionHandler of GlobalExceptionHandler");
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setErrorCode("500");
		errorDetails.setErrorDetails(ex.getMessage());
		return new ResponseEntity<Object>(new Response<Object>(errorDetails, "1"), HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
