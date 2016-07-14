package org.andresoviedo.tests.java9.process;

public class RecursiveJavaProcess {

	public static void main(String[] args) throws Exception {

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run(){
				System.out.println("Process "+ProcessHandle.current().getPid() +" is shut down!");
			}
		}));

		System.out.println("Running java process with PID " + ProcessHandle.current().getPid() + ". Parent: "
				+ ProcessHandle.current().parent().get().getPid());

		if (args == null || args.length == 0) {
			throw new IllegalArgumentException("usage java RecursiveJavaProcess N");
		}

		int it = Integer.parseInt(args[0]);
		if (it <= 0)
			System.exit(0);

		System.out.println("Sleeping 2 seconds...");
		Thread.sleep(2000);

		String java = "/usr/lib/jvm/java-9-oracle/bin/java";
		String cp = System.getProperty("java.class.path");
		StringBuilder output = new StringBuilder();
		RunHelper.execAndWait(java, output, "-cp", cp, RecursiveJavaProcess.class.getName(), String.valueOf(it - 1));
	}
}
