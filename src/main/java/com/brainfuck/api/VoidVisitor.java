package com.brainfuck.api;

import com.brainfuck.api.ast.*;

public interface VoidVisitor {
	void visit(Program program);
	void visit(IOCommand IOCommand);
	void visit(MathCommand mathCommand);
	void visit(MoveCommand moveCommand);
	void visit(LoopCommand loopCommand);
}
