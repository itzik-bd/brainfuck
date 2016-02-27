package com.brainfuck.api;

import com.brainfuck.parser.Token;
import com.brainfuck.api.exceptions.LexerException;

import java.util.List;

public interface Lexer {
	public List<Token> parse() throws LexerException;
}
