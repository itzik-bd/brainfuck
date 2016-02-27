package com.brainfuck.interpreter;

import java.util.ArrayList;
import java.util.List;

public class UnlimitedByteArray {
	private List<Byte> memory;
	private int position;

	public UnlimitedByteArray() {
		memory = new ArrayList<>();
		memory.add((byte) 0);
		position = 0;
	}

	public void moveForward() {
		if (isAtLastPosition()) {
			memory.add((byte) 0);
		}
		position++;
	}

	public void moveBackward() {
		if(!isAtFirstPosition()) {
			position--;
		}
	}

	public byte readValue() {
		return memory.get(position);
	}

	public void writeValue(Byte val) {
		memory.set(position, val);
	}

	private boolean isAtFirstPosition() {
		return (position == 0);
	}

	private boolean isAtLastPosition() {
		return (position == memory.size()-1);
	}

	@Override
	public String toString() {
		return String.format("Head at position %d - content: %s", position, memory);
	}
}
