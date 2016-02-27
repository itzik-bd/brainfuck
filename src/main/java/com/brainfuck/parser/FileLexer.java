package com.brainfuck.parser;

import com.brainfuck.api.Lexer;
import com.brainfuck.api.exceptions.LexerException;
import lombok.NonNull;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class FileLexer implements Lexer {

	private final File file;

	public FileLexer(@NonNull File file) {
		this.file = file;
	}

	public List<Token> parse() throws LexerException {
		String content;
		try {
			content = IOUtils.toString(new FileInputStream(file));
		}
		catch (IOException e) {
			throw new LexerException("Error while reading file", e);
		}

		Lexer lexer = new StringLexer(content);
		return lexer.parse();
	}
}
