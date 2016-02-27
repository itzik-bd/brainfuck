package com.brainfuck.api.ast;

import com.brainfuck.api.VoidVisitor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IOCommand extends AbstractNode {

	private IOType type;

	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}

	public static enum IOType {
		READ,
		WRITE;
	}
}
