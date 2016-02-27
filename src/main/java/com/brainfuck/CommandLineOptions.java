package com.brainfuck;

import org.apache.commons.cli.*;

public class CommandLineOptions {

	private static final String USAGE_STR = "java -jar brainfuck.jar [OPTIONS] <program>";
	private static final Options OPTIONS;

	public static final String OPTION_HELP = "help";
	public static final String OPTION_PRETTY_PRINT = "pretty-print";

	static {
		OPTIONS = new Options();
		OPTIONS.addOption(Option.builder()
				.longOpt(OPTION_HELP)
				.argName(OPTION_HELP)
				.desc("print help message and exit")
				.build());
		OPTIONS.addOption(Option.builder()
				.longOpt(OPTION_PRETTY_PRINT)
				.argName(OPTION_PRETTY_PRINT)
				.desc("pretty print program")
				.build());
	}

	public static CommandLine parse(String[] args) throws ParseException {
		return new DefaultParser().parse(OPTIONS, args);
	}

	public static void printHelp() {
		new HelpFormatter().printHelp(USAGE_STR, OPTIONS);
	}
}
