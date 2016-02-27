package com.brainfuck;

import org.apache.commons.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class MainTest {

	private ByteArrayOutputStream outContent;
	private ByteArrayOutputStream errContent;

	@Before
	public void setUpStreams() {
		outContent = new ByteArrayOutputStream();
		errContent = new ByteArrayOutputStream();
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
		System.setErr(null);
	}

	@Test
	public void noCliArgumentsTest() throws Exception {
		Main.main(new String[0]);
		assertTrue(outContent.toString().startsWith("usage:"));
	}

	@Test
	public void helpTest() throws Exception {
		Main.main(new String[] {"--help", "imaginary-program.bf"});
		assertTrue(outContent.toString().startsWith("usage:"));
	}

	@Test
	public void helloWorldRunTest() throws Exception {
		final String expectedOutput = IOUtils.toString(new FileInputStream(TestConstants.MAIN_TEST_HELLO_WORLD_OUTPUT_FILE.toFile())).replace("\r\n", "\n");
		Main.main(new String[] {TestConstants.MAIN_TEST_HELLO_WORLD_PROGRAM_FILE.toString()});
		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void helloWorldPrinterTest() throws Exception {
		final String expectedOutput = IOUtils.toString(new FileInputStream(TestConstants.MAIN_TEST_HELLO_WORLD_PRINTER_FILE.toFile())).replace("\r\n|\r|\n", System.lineSeparator());
		Main.main(new String[] {"--pretty-print", TestConstants.MAIN_TEST_HELLO_WORLD_PROGRAM_FILE.toString()});
		assertEquals(expectedOutput, outContent.toString());
	}

	@Test
	public void nonExistsProgramTest() throws Exception {
		Main.main(new String[] {"non-exists-program.bf"});
		assertTrue(errContent.toString().length() > 0);
	}

}