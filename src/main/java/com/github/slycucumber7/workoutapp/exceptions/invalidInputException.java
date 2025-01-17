package com.github.slycucumber7.workoutapp.exceptions;

public class invalidInputException extends RuntimeException {
	static final long serialVersionUID = 6887935528852468024L;

	public invalidInputException() {

	}
	public invalidInputException(String msg) {
		System.out.println(msg);
	}
}
