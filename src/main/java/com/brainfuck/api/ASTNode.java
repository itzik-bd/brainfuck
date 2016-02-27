package com.brainfuck.api;

public interface ASTNode {

	void accept(VoidVisitor visitor);

}
