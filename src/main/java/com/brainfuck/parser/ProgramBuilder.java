package com.brainfuck.parser;

import com.brainfuck.api.Lexer;
import com.brainfuck.api.Parser;
import com.brainfuck.api.ast.Program;
import com.brainfuck.api.exceptions.LexerException;
import com.brainfuck.api.exceptions.ParserException;
import lombok.AllArgsConstructor;

import java.io.File;

@AllArgsConstructor
public class ProgramBuilder {

	private Lexer lexer;
	private Parser parser;

	public Program build() throws LexerException, ParserException {
		return parser.parse(lexer.parse());
	}

	public static ProgramBuilder fromFile(File file) {
		return new ProgramBuilder(new FileLexer(file), new SimpleParser());
	}

	public static ProgramBuilder fromString(String program) {
		return new ProgramBuilder(new StringLexer(program), new SimpleParser());
	}

}
