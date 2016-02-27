package com.brainfuck.printer;

import com.brainfuck.TestConstants;
import com.brainfuck.api.ast.Program;
import com.brainfuck.parser.ProgramBuilder;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class TabPrinterTest {

	@Test
	public void TabPrinterRunTest() throws Exception {
		for (File programDirectory : TestConstants.TAB_PRINT_TESTS_DIRECTORY.toFile().listFiles(File::isDirectory)) {
			brainFuckSingleTabPrintTest(programDirectory.toPath());
		}
	}

	public void brainFuckSingleTabPrintTest(Path programDirectory) throws Exception {
		// get program test name
		String testName = programDirectory.getFileName().toString();

		// get relevant files
		final File programFile = programDirectory.resolve(Paths.get(TestConstants.PROGRAM_FILE)).toFile();
		final File outputFile = programDirectory.resolve(Paths.get(TestConstants.OUTPUT_FILE)).toFile();

		// parse program
		final Program p = ProgramBuilder.fromFile(programFile).build();

		// get expected result (make sure line separator is as current system configuration)
		final String expectedOutput = IOUtils.toString(new FileInputStream(outputFile)).replace("\r\n|\r|\n", System.lineSeparator());

		// convert program using tab printer
		final String tabbedProgram = new TabPrinter().convert(p);

		// assert actual and expected result
		assertEquals(String.format("tabbed print is not as expected (test '%s')", testName), expectedOutput, tabbedProgram);
	}

}