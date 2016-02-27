package com.brainfuck.api.exceptions;


public class LexerException extends Exception {
	public LexerException(String msg) {
		super(msg);
	}
	public LexerException(String msg, Exception e) {
		super(msg, e);
	}
	public LexerException(Exception e) {
		super(e);
	}
}
