package proj.tools.complexity;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.tools.data.ClassMetrics;
import proj.tools.parser.ClassParser;
import proj.tools.parser.DirectoryParser;

/**
 * This Analyzer contains the main method that analyzes a directory of different
 * projects using the Coupling Calculator , Cyclomatic Calculator, and Parsers
 * and generates a list of complexity based on classes of each project
 * 
 * @author cathykwok
 * 
 */
public class Analyzer {

	public static void main(String[] args) {

		// replace the next line with the correct path directory of projects
		String path = "/Users/cathykwok/Documents/School/CPSC410/";
		
		//complexityOfAllProj is used in the visualizer as input
		List<List<ClassMetrics>> complexityOfAllProj= analyze(path);
	}

	/**
	 * At the end of this method, there will be an list of ClassMetrics objects
	 * per project
	 * 
	 */
	private static List<List<ClassMetrics>> analyze(String path) {

		DirectoryParser projectParser = new DirectoryParser(path);
		List<String> commitIdPath = null;
		List<List<ClassMetrics>> complexityForProject = new ArrayList<List<ClassMetrics>>();
		
		//gets paths for each copy of the java project (based on commit ids) 
		try {
			commitIdPath = projectParser.getFolders();
		} catch (FileNotFoundException e) {
			System.out.println("FILE PATH ERROR");
			e.printStackTrace();
		}

		//analyzes each project
		for (int k = 0; k < commitIdPath.size(); k++) {
			
			// get all java files
			DirectoryParser dirParser = new DirectoryParser(commitIdPath.get(k));
			List<String> javaFilePaths = dirParser.getJavaFiles();

			List<String> javaClassList = dirParser.getClassList();
			ClassMetrics[] projectMetrics = new ClassMetrics[javaFilePaths
					.size()];
			ClassParser claParser = new ClassParser();

			// create a classMetrics object for each class
			for (int i = 0; i < javaFilePaths.size(); i++) {

				String commitId = commitIdPath.get(k);
				Map<String, Integer> complexityPerMethod = new HashMap<String, Integer>();
				Map<String, Integer> dependencyPerMethod = new HashMap<String, Integer>();

				// get all the methods for the class
				claParser.setFilePath(javaFilePaths.get(i));
				List<String> methodList = claParser.getMethodList();
				int linesOfCode = claParser.getLinesOfCode();

				// calculate the complexity for this class
				for (int j = 0; j < methodList.size(); j++) {
					// removes class constructors
					if (methodList.get(j).length() > 0
							&& (methodList.get(j).contains("void") || methodList
									.get(j).contains("return"))) {
						
						int complexity = CyclomaticCalculator
								.getCyclomaticCalc(methodList.get(j));

						complexityPerMethod.put(javaClassList.get(i) + j,
								complexity);

						int dependency = CouplingCalculator.getCouplingCalc(
								methodList.get(j), javaClassList);
						
						dependencyPerMethod.put(javaClassList.get(i) + j,
								dependency);
					}
				}

				projectMetrics[i] = new ClassMetrics(linesOfCode, commitId,
						complexityPerMethod, dependencyPerMethod);
			}

			complexityForProject.add(Arrays.asList(projectMetrics));
		}
		
		return complexityForProject;

	}

}
