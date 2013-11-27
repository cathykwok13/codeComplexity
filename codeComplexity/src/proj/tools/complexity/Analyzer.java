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

public class Analyzer {

	public static void main(String[] args) {

		String path = "G:/CPSC410/CPSC410";
		analyze(path);
	}

	/**
	 * At the end of this method, there will be an array of ClassMetrics objects per project
	 * 
	 * @param path
	 */
	public static List<List<ClassMetrics>> analyze(String path) {

		DirectoryParser projectParser = new DirectoryParser(path);
		List<String> commitIdPath = null;
		try {
			commitIdPath = projectParser.getFolders(path);
		} catch (FileNotFoundException e) {
			System.out.println("FILE PATH ERROR");
			e.printStackTrace();
		}

		// FOR EACH PROJECt
		List<List<ClassMetrics>> complexityForProject = new ArrayList<List<ClassMetrics>>();

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

				// values required for ClassMetrics
				
				String commitId = commitIdPath.get(k);
				Map<String, Integer> complexityPerMethod = new HashMap<String, Integer>();
				Map<String, Integer> dependencyPerMethod = new HashMap<String, Integer>();

				// get all the methods for the class
				claParser.setFilePath(javaFilePaths.get(i));
				List<String> methodList = claParser.getMethodList();
				int linesOfCode = claParser.getLinesOfCode();

				// TODO
				// calculate the cyclomatic complexity for this class
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

//				System.out.println("CLASS ***** " + javaClassList.get(i));
//				System.out.println("Lines Of Code: "
//						+ projectMetrics[i].getLinesOfCode());
//				System.out.println("Commit Id: "
//						+ projectMetrics[i].getCommitId());
//				System.out.println("Cyclomatic Complexity: "
//						+ projectMetrics[i].getComplexityPerMethod());
//				System.out.println("Coupling : "
//						+ projectMetrics[i].getDependencyPerMethod());

			}

			 complexityForProject.add(Arrays.asList(projectMetrics));
		}
		
		return complexityForProject;
	}

}
