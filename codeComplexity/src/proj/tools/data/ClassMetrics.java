package proj.tools.data;

import java.util.Map;

/**
 * 
 * Object used to store complexity information for each class
 * @author cathykwok
 *
 */
public class ClassMetrics {
	
	int linesOfCode;
	String commitId;
	
	Map<String, Integer> complexityPerMethod;
	Map<String, Integer> dependencyPerMethod;
	
	public ClassMetrics( int linesOfCode, String commitId, Map<String, Integer> complexityPerMethod, Map<String, Integer> dependencyPerMethod){
		this.linesOfCode = linesOfCode;
		this.commitId = commitId;
		this.complexityPerMethod = complexityPerMethod;
		this.dependencyPerMethod = dependencyPerMethod;
	}
	
	public int getLinesOfCode(){
		return linesOfCode;
	}
	
	public String getCommitId(){
		return commitId;
	}
	
	public Map<String, Integer> getComplexityPerMethod(){
		return complexityPerMethod;
	}
	
	public Map<String, Integer> getDependencyPerMethod(){
		return  dependencyPerMethod;
	}
	
}
