package com.brainfuck.api.ast;

import com.brainfuck.api.ASTNode;
import com.brainfuck.api.VoidVisitor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Program implements ASTNode {

	List<ASTNode> commands;

	@Override
	public void accept(VoidVisitor visitor) {
		visitor.visit(this);
	}
}
