package proj.tools.data;

import java.util.HashMap;
import java.util.Map;

public class ClassMetrics {

	public int linesOfCode;
	
	public String commitId;
	
	public Map<String, Integer> complexityPerMethod = new HashMap<>();
	
	public Map<String, Integer> dependenciesPerMeothd = new HashMap<>();
	
	
}
