package complexity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {
	
	public static void main(String[] args){
		
		List<String> code = new ArrayList<String>();
		String word = "";
		String nextWord = "";
	  
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(
					new File("/home/cathy/Documents/6th YEAR/CPSC410/test/School.java"));
			
			boolean isBracket = false;
			while(fileScanner.hasNext() && !isBracket){
				nextWord = fileScanner.next();
				if(nextWord.contains("{"))
					isBracket = true;
				word = word + " " + nextWord;
			}
			
			code.add(word);
			System.out.println(word);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		

		
	}

	
}

