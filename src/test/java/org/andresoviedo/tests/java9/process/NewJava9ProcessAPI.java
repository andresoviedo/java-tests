package org.andresoviedo.tests.java9.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class NewJava9ProcessAPI {

	public static void main(String[] args) {
//		getAllProcesses();
//		getAllProcessesWithChilds();
		createAndDestroyProcessTree();
		// TODO: dealWithHundredOfProcesses()
	}

	public static List<Process> getAllProcesses() {
		System.out.println("Process list:");

		java.lang.ProcessHandle.allProcesses()
				.forEach(e -> System.out.println(e.getPid() + " " + e.info().commandLine()));

		return null;
	}

	public static List<Process> getAllProcessesWithChilds() {
		System.out.println("Process list:");

		java.lang.ProcessHandle.allProcesses().filter(e -> e.children().count() > 0).forEach(
				e -> System.out.println(e.getPid() + " " + e.info().commandLine() + " " + e.children().count()));

		return null;
	}

	public static void createAndDestroyProcessTree() {

		// create process tree
		String java = "/usr/lib/jvm/java-9-oracle/bin/java";
		String cp = System.getProperty("java.class.path");
		StringBuilder output = new StringBuilder();
		
		try {
			Process ptree = RunHelper.exec(java, output, "-cp", cp, RecursiveJavaProcess.class.getName(), "3");
			Thread.sleep(4500);
			ptree.destroy();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public static void createHundredOfProcesses(){
		// TODO:
	}
}

/**
 * InputStream reader and Appendable writer thread
 * 
 * @see http://www.javaworld.com/article/2071275/core-java/when-runtime-exec
 *      ---won-t.html
 */
class StreamGobbler extends Thread {

	InputStream is;
	String type;
	Appendable output;

	public StreamGobbler(InputStream is, String type) {
		this(is, type, null);
	}

	public StreamGobbler(InputStream is, String type, Appendable output) {
		this.is = is;
		this.type = type;
		this.output = output;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			// String line = null;
			// while ((line = br.readLine()) != null)
			// logger.debug("<" + type + "> " + line);
			char[] charBuffer = new char[1];
			while (br.read(charBuffer, 0, 1) != -1) {
				// logger.debug("<" + type + "> " + line);
				if (output != null) {
					output.append(new String(charBuffer));
				}
				System.out.print(new String(charBuffer));
			}
		} catch (IOException ex) {
			System.err.println("Exception while reading stream."+ex.getMessage());
		}
	}
}

class RunHelper {

	public static int execAndWait(String file, Appendable output, String... args) throws Exception {
		String[] cmdArray = new String[args != null ? args.length + 1 : 1];
		cmdArray[0] = file;
		if (args != null) {
			System.arraycopy(args, 0, cmdArray, 1, args.length);
		}
		return execAndWait(cmdArray, output);
	}

	public static Process exec(String file, Appendable output, String... args) throws Exception {
		String[] cmdArray = new String[args != null ? args.length + 1 : 1];
		cmdArray[0] = file;
		if (args != null) {
			System.arraycopy(args, 0, cmdArray, 1, args.length);
		}
		return exec(cmdArray, output);
	}

	public static int execBat(String batFile, Appendable output, String... args) throws Exception {
		String[] cmdArray = new String[3];
		cmdArray[0] = "cmd.exe";
		cmdArray[1] = "/c";
		cmdArray[2] = "\"";
		cmdArray[2] += "\"" + batFile + "\"";
		if (args != null) {
			for (String arg : args) {
				if (arg.contains(" ")) {
					cmdArray[2] += " \"" + arg + "\"";
				} else {
					cmdArray[2] += " " + arg;
				}
			}
		}
		cmdArray[2] += "\"";
		return execAndWait(cmdArray, output);
	}

	public static int execAndWait(String[] cmdArray, Appendable output) throws Exception {
		Process pr = exec(cmdArray, output);

		int status = pr.waitFor();

		System.out.println("Process " + pr.getPid() + " finished with status '" + status + "'.");
		return status;
	}

	public static Process exec(String[] cmdArray, Appendable output) throws Exception {
		System.out.println("Executing " + java.util.Arrays.toString(cmdArray) + "'...");

		Runtime rt = Runtime.getRuntime();
		Process pr = rt.exec(cmdArray);

		StreamGobbler stdOutReader = new StreamGobbler(pr.getInputStream(), "STDOUT", output);
		StreamGobbler stdErrReader = new StreamGobbler(pr.getErrorStream(), "STDERR");

		stdOutReader.start();
		stdErrReader.start();

		return pr;

	}
}