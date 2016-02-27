package com.brainfuck;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TestConstants {

	public static final Path BASE_TEST_RESOURCES_DIR = Paths.get("src", "test", "resources");

	public static final Path RUN_PROGRAMS_TESTS_DIRECTORY = BASE_TEST_RESOURCES_DIR.resolve(Paths.get("interpreter"));
	public static final Path TAB_PRINT_TESTS_DIRECTORY = BASE_TEST_RESOURCES_DIR.resolve(Paths.get("printer"));

	public static final String PROGRAM_FILE = "program.bf";
	public static final String INPUT_FILE = "input.txt";
	public static final String OUTPUT_FILE = "output.txt";

	public static final Path MAIN_TEST_HELLO_WORLD_PROGRAM_FILE = BASE_TEST_RESOURCES_DIR.resolve(Paths.get("main", "helloWorld.bf"));
	public static final Path MAIN_TEST_HELLO_WORLD_OUTPUT_FILE = BASE_TEST_RESOURCES_DIR.resolve(Paths.get("main", "helloWorld-output.txt"));
	public static final Path MAIN_TEST_HELLO_WORLD_PRINTER_FILE = BASE_TEST_RESOURCES_DIR.resolve(Paths.get("main", "helloWorld-printer.txt"));

}
