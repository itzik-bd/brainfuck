package com.brainfuck.parser;

import com.brainfuck.api.ASTNode;
import com.brainfuck.api.Parser;
import com.brainfuck.api.ast.*;
import com.brainfuck.api.exceptions.ParserException;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class SimpleParser implements Parser {

	private Stack<List<ASTNode>> loopCommandsStack;

	public Program parse(final List<Token> tokens) throws ParserException {
		// create levels stack with one level - the program level
		loopCommandsStack = new Stack<>();
		loopCommandsStack.push(new LinkedList<>());

		// handle each token
		for(Token t : tokens) {
			switch(t) {
				case RIGHT:
					addCommand(MoveCommand.builder().type(MoveCommand.MoveType.RIGHT).build());
					break;
				case LEFT:
					addCommand(MoveCommand.builder().type(MoveCommand.MoveType.LEFT).build());
					break;
				case INCREMENT:
					addCommand(MathCommand.builder().type(MathCommand.MathType.INCREMENT).build());
					break;
				case DECREMENT:
					addCommand(MathCommand.builder().type(MathCommand.MathType.DECREMENT).build());
					break;
				case OUTPUT:
					addCommand(IOCommand.builder().type(IOCommand.IOType.WRITE).build());
					break;
				case INPUT:
					addCommand(IOCommand.builder().type(IOCommand.IOType.READ).build());
					break;
				case LOOP_START:
					createLevel();
					break;
				case LOOP_END:
					addCommand(LoopCommand.builder().commands(removeLevel()).build());
					break;
			}
		}

		// at this point only one level should exists
		if (loopCommandsStack.size() != 1) {
			throw new ParserException("Failed to parse program");
		}

		// return a program with the level commands
		return Program.builder().commands(removeLevel()).build();
	}

	private void addCommand(ASTNode node) {
		loopCommandsStack.peek().add(node);
	}

	private List<ASTNode> removeLevel() throws ParserException {
		try {
			return loopCommandsStack.pop();
		}
		catch (EmptyStackException e) {
			throw new ParserException("Failed to parse program", e);
		}
	}

	private void createLevel() {
		loopCommandsStack.push(new LinkedList<>());
	}
}
