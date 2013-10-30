package proj.tools.complexity;

import java.util.List;
import java.util.Map;

import proj.tools.data.ClassMetrics;

public class Analyzer {

	public static void main(String[] args) {

		String path = "/home/cathy/Documents/6th YEAR/CPSC410/GoldenApple/";
		analyze(path);
		// List<String> javaFiles = getJavaFiles(projectDirectory);
		// showFiles(projectDirectory);
	}

	/**
	 * At the end of this method, there will be an array of ClassMetrics objects
	 * @param path
	 */
	private static void analyze(String path) {

		// get all java files
		DirectoryParser dirParser = new DirectoryParser(path);
		List<String> javaFilePaths = dirParser.getJavaFiles();
		ClassMetrics[] projectMetrics = new ClassMetrics[javaFilePaths.size()];
		ClassParser claParser = new ClassParser();

		// FOR EACH java file, create a classMetrics object

		for (int i = 0; i < javaFilePaths.size(); i++) {
			// values this code has to fill in for ClassMetrics
			int linesOfCode=0;
			String commitId="";
			Map<String, Integer> complexityPerMethod = null;
			Map<String, Integer> dependencyPerMethod = null;

			//TODO
			//get all the methods for the class
			claParser.setFilePath(javaFilePaths.get(i));
			//TODO
			// calculate the cyclomatic complexity for this class
			

			projectMetrics[i] = new ClassMetrics(linesOfCode, commitId,
					complexityPerMethod, dependencyPerMethod);
		}

	}

}
