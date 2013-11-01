package proj.tools.data;

import java.util.Map;

public class ClassMetrics {
	
	private int linesOfCode;
	private String commitId;
	
	private Map<String, Integer> complexityPerMethod;
	private Map<String, Integer> dependencyPerMethod;
	
	public ClassMetrics( int linesOfCode, String commitId,Map<String, Integer> complexityPerMethod,Map<String, Integer> dependencyPerMethod){
		this.linesOfCode = linesOfCode;
		this.commitId = commitId;
		this.complexityPerMethod = complexityPerMethod;
		this.dependencyPerMethod = dependencyPerMethod;
	}
}
