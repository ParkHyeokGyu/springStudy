package com.sist.commons;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonsException {
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException e) {
		System.out.println("========== RuntimeException ==========");
		e.printStackTrace();
		System.out.println("======================================");
	}
	@ExceptionHandler(IOException.class)
	public void ioException(IOException e) {
		System.out.println("============= IOException ============");
		e.printStackTrace();
		System.out.println("======================================");
	}
	@ExceptionHandler(SQLException.class)
	public void sqlException(SQLException e) {
		System.out.println("============ SQLException ============");
		e.printStackTrace();
		System.out.println("======================================");
	}
}
