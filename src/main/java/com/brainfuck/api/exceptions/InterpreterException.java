package com.brainfuck.api.exceptions;

public class InterpreterException extends RuntimeException {
	public InterpreterException(String msg) {
		super(msg);
	}
	public InterpreterException(String msg, Exception e) {
		super(msg, e);
	}
	public InterpreterException(Exception e) {
		super(e);
	}
}
