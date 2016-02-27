package com.brainfuck.parser;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Token {
	
	RIGHT('>'),
	LEFT('<'),
	INCREMENT('+'),
	DECREMENT('-'),
	OUTPUT('.'),
	INPUT(','),
	LOOP_START('['),
	LOOP_END(']');

	private Character tokenChar;

	private Token(Character tokenChar) {
		this.tokenChar = tokenChar;
	}

	private static Map<Character, Token> tokenMap;

	static {
		tokenMap = new HashMap<>();
		for(Token t : values()) {
			tokenMap.put(t.tokenChar, t);
		}
	}

   public static Optional<Token> parse(Character c) {
		return Optional.ofNullable(tokenMap.get(c));
	}
}
