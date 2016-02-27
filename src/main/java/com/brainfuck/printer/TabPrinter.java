package com.brainfuck.printer;

import com.brainfuck.api.VoidVisitor;
import com.brainfuck.api.ast.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class TabPrinter implements VoidVisitor {

	private int indentation;
	private List<String> lines;

	public String convert(Program program) {
		// init
		indentation = 0;
		lines = new LinkedList<>();

		// convert program
		visit(program);

		// return string
		return String.join(System.lineSeparator(), lines);
	}

	@Override
	public void visit(Program program) {
		program.getCommands().forEach(c -> c.accept(this));
	}

	@Override
	public void visit(LoopCommand loopCommand) {
		indentLine("[");
		indentation++;
		loopCommand.getCommands().forEach(c -> c.accept(this));
		indentation--;
		indentLine("]");
	}

	@Override
	public void visit(IOCommand IOCommand) {
		switch (IOCommand.getType()) {
			case READ:
				indentLine(",");
				break;
			case WRITE:
				indentLine(".");
				break;
		}
	}

	@Override
	public void visit(MathCommand mathCommand) {
		switch (mathCommand.getType()) {
			case INCREMENT:
				indentLine("+");
				break;
			case DECREMENT:
				indentLine("-");
				break;
		}
	}

	@Override
	public void visit(MoveCommand moveCommand) {
		switch (moveCommand.getType()) {
			case RIGHT:
				indentLine(">");
				break;
			case LEFT:
				indentLine("<");
				break;
		}
	}

	private void indentLine(String str) {
		lines.add(String.join("", Collections.nCopies(indentation, "\t")) + str);
	}
}
