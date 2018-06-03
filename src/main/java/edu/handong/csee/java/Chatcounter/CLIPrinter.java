package edu.handong.csee.java.Chatcounter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
/**
 * 
 * This class will implement through argument.
 * 
 * @author miin
 *
 */
public class CLIPrinter {

	String filePath;
	String filename;
	boolean help;
	boolean verbose;

	
	public void run(String[] args) {
		Options options = createOptions();

		if (parseOptions(options, args)) {
			if (help) {
				printHelp(options);
				return;
			}

		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {
			CommandLine cmd = parser.parse(options, args);

			filePath = cmd.getOptionValue("i");
			filename = cmd.getOptionValue("o");
			help = cmd.hasOption("h");
		}

		catch (Exception e) {
			printHelp(options);
			return false;
		}
		return true;
	}

	private Options createOptions() {
		Options options = new Options();

		options.addOption(Option.builder("i").longOpt("filePath").desc("Set a path of a directory or a file to display")
				.hasArg().argName("Path name to display").required().build());

		options.addOption(Option.builder("o").longOpt("filename").desc("count the kakao chat").hasArg()
				.argName("ChatCounter").required().build());

		options.addOption(Option.builder("h").longOpt("help").desc("Help").build());

		return options;
	}

	private void printHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		String header = "This is a Kakao ChatCounter!";
		String footer = "\nPlease report issues at https://github.com/Bigstargithub/ChatCounter";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}

	
	public String getfilepath() {
		return filePath;
	}

	public String getfilename() {
		return filename;
	}

}