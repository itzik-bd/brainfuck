package com.brainfuck.api;

import com.brainfuck.parser.Token;
import com.brainfuck.api.ast.Program;
import com.brainfuck.api.exceptions.ParserException;

import java.util.List;

public interface Parser {

	public Program parse(final List<Token> tokens) throws ParserException;

}
