package proj.tools.complexity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassParser {

	File fileToAnalyze;
	
	public ClassParser() {
	}

	protected void setFilePath(String filePath) {
		this.fileToAnalyze = new File(filePath);
	}

	protected List<String> getMethodList() {
		List<String> code = new ArrayList<String>();
		String word = "";
		String nextWord = "";

		Scanner fileScanner;
		try {
			fileScanner = new Scanner(fileToAnalyze);

			boolean isBracket = false;
			while (fileScanner.hasNext() && !isBracket) {
				nextWord = fileScanner.next();
				if (nextWord.contains("{"))
					isBracket = true;
				word = word + " " + nextWord;
			}

			code.add(word);
			System.out.println(word);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return code;

	}

}
