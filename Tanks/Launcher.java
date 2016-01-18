package tanks;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Launcher  {

	public boolean COLORED_MODE = true;

//	String absolutePath = System.getProperty("user.dir");
//	public final String LOG_NAME = System.getProperty("user.dir") + "\\" + "LogTanks.lg";
	//final PrintStream printStream = new PrintStream(new File(LOG_NAME));

	public static void main(String[] args) throws Exception {
		Launcher launcher = new Launcher();
	}

	Launcher() throws Exception {

		//System.setOut(printStream);
//		FileOutputStream fileOutputStream = new FileOutputStream(LOG_NAME);
//		fileOutputStream.close();

		ActionField actionField = new ActionField();

	}

}
