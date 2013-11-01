package proj.tools.complexity;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CouplingCalculator {
	
	/**
	 * I couldn't work out how to make a parser,
	 * but I assume we will want a list of methods for each class.
	 */
	private List<List<String>> methodLists;
	
	/**
	 * It would also be useful to keep a list of the Files,
	 * I don't know if this is handled anywhere else.
	 */
	private List<File> fileList;
	
	
	/**
	 * Creates a list of the coupling of each file
	 * @param ml A list containing lists of methods for each class
	 * @param fl List of the files to be inspected
	 * @return A List of the coupling for each file
	 */
	public List<Integer> getCoupling(List<List<String>> ml, List<File> fl) {
		methodLists = ml;
		fileList = fl;
		int i;
		int j;
		int k;
		int tempCoupling = 0;
		int coupling = 0;
		List<Integer> Coupling = new ArrayList<Integer>();
		List<String> classToInspect;
		String methodToCompare;
		File fileToCalculate;
		
		
		for(i = 0; i < fileList.size(); i++) {
			fileToCalculate = fileList.get(i);
			tempCoupling = 0;
			coupling = 0;
			for(j = 0; j < methodLists.size(); j++) {
				if(j != i) {
					classToInspect = methodLists.get(j);
					for(k = 0; k < classToInspect.size(); k++) {
						methodToCompare = classToInspect.get(k);
						Scanner fileScanner;
						try {
							fileScanner = new Scanner(fileToCalculate);
							while(fileScanner.hasNext()) {
								if(fileScanner.next().contains(methodToCompare)) {
									tempCoupling = 1;
									break;
								} else
									tempCoupling = 0;
							}
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						}
					}
				}
				coupling = coupling + tempCoupling;
			}
			Coupling.add(i, tempCoupling);
		}
		return Coupling;
	}
}