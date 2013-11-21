package proj.tools.complexity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.tools.data.ClassMetrics;
import proj.tools.parser.ClassParser;
import proj.tools.parser.DirectoryParser;

public class Analyzer {

	public static void main(String[] args) {

		String path = "/home/cathy/Documents/6th YEAR/CPSC410/GoldenApple/";
		analyze(path);
	}

	/**
	 * At the end of this method, there will be an array of ClassMetrics objects
	 * 
	 * @param path
	 */
	private static void analyze(String path) {

		// get all java files
		DirectoryParser dirParser = new DirectoryParser(path);
		List<String> javaFilePaths = dirParser.getJavaFiles();
		ClassMetrics[] projectMetrics = new ClassMetrics[javaFilePaths.size()];
		ClassParser claParser = new ClassParser();

		// create a classMetrics object for each class

		for (int i = 0; i < javaFilePaths.size(); i++) {

			// values required for ClassMetrics
			int linesOfCode = 0;
			String commitId = "";
			Map<String, Integer> complexityPerMethod = new HashMap<String, Integer>();
			Map<String, Integer> dependencyPerMethod = new HashMap<String, Integer>();

			// get all the methods for the class
			claParser.setFilePath(javaFilePaths.get(i));
			List<String> methodList = claParser.getMethodList();

			// TODO
			// calculate the cyclomatic complexity for this class
			for (int j = 0; j < methodList.size(); j++) {
				// removes class constructors
				if (methodList.get(j).length() > 0
						&& (methodList.get(j).contains("void") || methodList
								.get(j).contains("return"))) {
					int complexity = CyclomaticCalculator
							.getCyclomaticCalc(methodList.get(j));

					complexityPerMethod.put(methodList.get(j), complexity);

					// int dependency =
					// CouplingCalculator.getCouplingCalc(methodList.get(j));
					// dependencyPerMethod.out(methodList.get(j),dependency);

				}
			}

			// TODO fill in values of class metrics
			projectMetrics[i] = new ClassMetrics(linesOfCode, commitId,
					complexityPerMethod, dependencyPerMethod);

		}

	}

}
