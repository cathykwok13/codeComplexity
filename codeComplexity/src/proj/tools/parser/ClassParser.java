package proj.tools.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ClassParser {

	File fileToAnalyze;

	public ClassParser() {
	}

	public void setFilePath(String filePath) {
		this.fileToAnalyze = new File(filePath);
	}
	
	public int getLinesOfCode() {
		int count = 0;
		
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(fileToAnalyze);
			while(fileScanner.hasNextLine()){
				count++;
				fileScanner.nextLine();
			}
			fileScanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<String> getMethodList() {
		List<String> code = new ArrayList<String>();
		String nextWord = "";

		Scanner fileScanner;
		try {
		
			fileScanner = new Scanner(fileToAnalyze);
			
			// first search for the opening bracket of the class (after the first bracket)
			boolean isBracket = false;
			while (fileScanner.hasNext() && !isBracket) {
				nextWord = fileScanner.next();
				if (nextWord.contains("{"))
					isBracket = true;
			}

			// inside the class
			boolean isMethod = false;
			boolean usingTemp = false;
			String method = "";
			int nestedBracket = 0;

			while (fileScanner.hasNext()) {
				nextWord = fileScanner.next();

				//saves public/private/protected in case it is for a method
				if ((nextWord.contains("public")
						|| nextWord.contains("private") || nextWord
							.contains("protected")) && !isMethod) {
					method = "";
					method = method + " " + nextWord;
					usingTemp = true;
				} else if (usingTemp) {
					method = method + " " + nextWord;
				}

				
				//looks for opening brackets of methods
				if (nextWord.contains("{") && !isMethod) {
					isMethod = true;
				} else if (isMethod) {
					if (nextWord.contains("{") ) {
						nestedBracket++;
					} else if (nextWord.contains("}") && nestedBracket > 0) {
						nestedBracket--;
					} else if (nextWord.contains("}") && nestedBracket == 0) {
						isMethod = false;
						code.add(method);
						usingTemp = false;
						method = "";
					}
				}
			}
			fileScanner.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		return code;

	}

}