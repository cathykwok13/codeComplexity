package proj.tools.parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BugReportParser {

	String[] commitId;
	String[] commitDate;

	public BugReportParser(){}

	// Read commitId from the bug report
	public static String[] readCommitId(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String[] idStorage = new String[10];
		int i = 0;

		while(input.hasNextLine()) {
			idStorage[i] = input.nextLine().substring(13, 24);
			i++;
		}
		input.close();

		return idStorage;
	}

	// Map the commitId to its date
	public static String[] readDate(File file, String[] commitId) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String[] temp = new String[10];
		String[] dateStorage = new String[10];
		int i = 0;

		while(input.hasNext()) {
			temp[i] = input.nextLine();
			i++;
		}

		for(int n=0; n<temp.length; n++) {
			if(commitId[n].equals(temp[n].substring(13, 24))) {
				dateStorage[n] = temp[n].substring(0, 12);
			}
		}
		input.close();

		return dateStorage;
	}
}