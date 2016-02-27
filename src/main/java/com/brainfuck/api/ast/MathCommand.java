package com.brainfuck.api.ast;

import com.brainfuck.api.VoidVisitor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MathCommand extends AbstractNode {

	private MathType type;

	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}

	public static enum MathType {
		INCREMENT,
		DECREMENT;
	}
}
