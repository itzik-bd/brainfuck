package com.brainfuck.parser;

import com.brainfuck.api.Lexer;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StringLexer implements Lexer {

	private final String input;

	public StringLexer(@NonNull String input) {
		this.input = input;
	}

	public List<Token> parse() {
		return input.chars()
				.mapToObj(e->(char) e)
				.map(Token::parse)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.collect(Collectors.toList());
	}

}
