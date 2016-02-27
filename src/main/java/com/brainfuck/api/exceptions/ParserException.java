package com.brainfuck.api.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ParserException extends Exception {
	public ParserException(String msg) {
		super(msg);
	}
	public ParserException(String msg, Exception e) {
		super(msg, e);
	}
	public ParserException(Exception e) {
		super(e);
	}

}
