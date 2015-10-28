package Tanks;


import java.io.File;
import java.io.PrintStream;

public class Launcher  {

	public boolean COLORED_MODE = true;

	String absolutePath = System.getProperty("user.dir");

	final String LOG_NAME = absolutePath + "\\" + "LogTanks.lg";
	final PrintStream printStream = new PrintStream(new File(LOG_NAME));

	public static void main(String[] args) throws Exception {
		Launcher launcher = new Launcher();
	}

	Launcher() throws Exception {

		System.setOut(printStream);

		ActionField actionField = new ActionField();

	}

}
