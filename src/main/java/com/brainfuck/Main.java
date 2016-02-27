package com.brainfuck;

import com.brainfuck.api.ast.Program;
import com.brainfuck.api.exceptions.LexerException;
import com.brainfuck.api.exceptions.ParserException;
import com.brainfuck.interpreter.Interpreter;
import com.brainfuck.parser.ProgramBuilder;
import com.brainfuck.printer.TabPrinter;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.ParseException;

import java.io.File;

public class Main {

	public static void main (String[] args) throws ParseException, LexerException, ParserException {
		// parse cli arguments
		CommandLine cmd = CommandLineOptions.parse(args);
		String[] remainingArgs = cmd.getArgs();

		// check if to print help message
		if (cmd.hasOption(CommandLineOptions.OPTION_HELP) || remainingArgs.length == 0) {
			CommandLineOptions.printHelp();
			return;
		}

		// get program file path string
		File program = new File(cmd.getArgList().get(0));

		// make sure the program file exists
		if (!program.exists()) {
			System.err.println(String.format("No brainfuck program '%s' was not found", program));
			return;
		}
		else if (!program.isFile()) {
			System.err.println(String.format("brainfuck program '%s' must be a file", program));
			return;
		}

		// parse program
		final Program p = ProgramBuilder.fromFile(program).build();

		// pretty print program if required (and exit)
		if (cmd.hasOption(CommandLineOptions.OPTION_PRETTY_PRINT)) {
			System.out.print(new TabPrinter().convert(p));
			return;
		}

		// run program
		final Interpreter interpreter = new Interpreter(System.in, System.out);
		interpreter.visit(p);
	}
}
