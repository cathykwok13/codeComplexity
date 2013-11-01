package proj.tools.parser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class BugReportParser {

	private static String[] commitId;
	//private static String[] commitDate;

	public static void main(String[] args) throws FileNotFoundException {
		File bugReport = new File("/Users/selenalee/Documents/Cpsc 410/Project/Bug Report.txt");
		commitId = readCommitId(bugReport);
		//readDate(bugReport, commitId);

	}

	// Read commitId from the bug report
	public static String[] readCommitId(File file) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String next;
		String[] storage = new String[20];
		
		while(input.hasNextLine()) {
			next = input.nextLine();
			System.out.println(next);
		}
		input.close();
		return storage;
	}

	// Map the commitId to its date
	/*	public static String readDate(File file, String commitId) throws FileNotFoundException {
		Scanner input = new Scanner(file);
		String next;

		while(input.hasNext()) {
			next = input.next();
			System.out.println(next);
		}
		input.close();
		return commitDate;
	}*/
}