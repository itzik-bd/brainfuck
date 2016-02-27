package com.brainfuck.interpreter;

import com.brainfuck.TestConstants;
import com.brainfuck.api.ast.Program;
import com.brainfuck.parser.ProgramBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class InterpreterTest {

	@Test
	public void InterpreterRunTest() throws Exception {
		for (File programDirectory : TestConstants.RUN_PROGRAMS_TESTS_DIRECTORY.toFile().listFiles(File::isDirectory)) {
			brainFuckSingleRunTest(programDirectory.toPath());
		}
	}

	public void brainFuckSingleRunTest(Path programDirectory) throws Exception {
		// get program test name
		String testName = programDirectory.getFileName().toString();

		// get relevant files
		final File programFile = programDirectory.resolve(Paths.get(TestConstants.PROGRAM_FILE)).toFile();
		final File inputFile = programDirectory.resolve(Paths.get(TestConstants.INPUT_FILE)).toFile();
		final File outputFile = programDirectory.resolve(Paths.get(TestConstants.OUTPUT_FILE)).toFile();

		// parse program
		final Program p = ProgramBuilder.fromFile(programFile).build();

		// create input and output streams (if input file is missing use empty input stream)
		final InputStream inputStream = inputFile.isFile() ? new FileInputStream(inputFile) : new ByteArrayInputStream(new byte[0]);
		final OutputStream outputStream = new ByteArrayOutputStream();

		// get expected result (Unix style - replace CRLF to LF)
		final String expectedOutput = IOUtils.toString(new FileInputStream(outputFile)).replace("\r\n","\n");

		// run program
		final Interpreter interpreter = new Interpreter(inputStream, outputStream);
		interpreter.visit(p);

		// get program output
		final String output = outputStream.toString();

		// assert actual and expected result
		assertEquals(String.format("program output is not as expected (test '%s')", testName), expectedOutput, output);
	}

}