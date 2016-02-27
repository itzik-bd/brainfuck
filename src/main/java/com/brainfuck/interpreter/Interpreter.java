package com.brainfuck.interpreter;

import com.brainfuck.api.VoidVisitor;
import com.brainfuck.api.ast.*;
import com.brainfuck.api.exceptions.InterpreterException;
import lombok.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Interpreter implements VoidVisitor {

	private InputStream input;
	private OutputStream output;
	private UnlimitedByteArray memory;

	public Interpreter(@NonNull InputStream input, @NonNull OutputStream output) {
		this.input = input;
		this.output = output;
		this.memory = new UnlimitedByteArray();
	}

	@Override
	public void visit(Program program) {
		program.getCommands().forEach(c -> c.accept(this));
	}

	@Override
	public void visit(IOCommand IOCommand) {
		try {
			switch(IOCommand.getType()) {
				case READ:
					int val = input.read();
					// if end of input encountered use zero instead
					if (val == -1) {
						val = 0;
					}
					memory.writeValue((byte) val);
					break;
				case WRITE:
					output.write(memory.readValue());
					break;
			}
		}
		catch (IOException e) {
			throw new InterpreterException("Error while reading input", e);
		}
	}

	@Override
	public void visit(MathCommand mathCommand) {
		byte cell = memory.readValue();
		switch(mathCommand.getType()) {
			case INCREMENT:
				cell++;
				break;
			case DECREMENT:
				cell--;
				break;
		}
		memory.writeValue(cell);
	}

	@Override
	public void visit(MoveCommand moveCommand) {
		switch (moveCommand.getType()) {
			case RIGHT:
				memory.moveForward();
				break;
			case LEFT:
				memory.moveBackward();
				break;
		}
	}

	@Override
	public void visit(LoopCommand loopCommand) {
		while(memory.readValue() != (byte) 0) {
			loopCommand.getCommands().forEach(c -> c.accept(this));
		}
	}

}
