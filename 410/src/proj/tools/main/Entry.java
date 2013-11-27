package proj.tools.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import proj.tools.complexity.Analyzer;
import proj.tools.data.ClassMetrics;
import proj.tools.viz.GalaxyVisualization;

public class Entry {

	public static final boolean TEST_QUESTION = false;
	
	public static void main(String[] args){
		List<List<ClassMetrics>> metrics;
		
		if(TEST_QUESTION){
			metrics = generateTestData();
		}else{
			metrics = Analyzer.analyze("G:/CPSC410/CPSC410");			
		}
		GalaxyVisualization visualization = new GalaxyVisualization(metrics);
		visualization.start();
	}
	
	private static List<List<ClassMetrics>> generateTestData(){
		List<List<ClassMetrics>> metrics = new ArrayList<>();
				
		//first commit
		{		
			List<ClassMetrics> firstCommit = new ArrayList<>();

			//first commit, class 1
			{
				Map<String, Integer> complexityPerMethod = new HashMap<>();
				Map<String, Integer> dependencyPerMethod = new HashMap<>();
				
				complexityPerMethod.put("A", 100);
				dependencyPerMethod.put("A", 100);
				
				complexityPerMethod.put("B", 0);
				dependencyPerMethod.put("B", 0);
				
				ClassMetrics cm = new ClassMetrics(600, "0", complexityPerMethod, dependencyPerMethod);
				
				firstCommit.add(cm);
			}
			
			//first commit, class 2
			{
				Map<String, Integer> complexityPerMethod = new HashMap<>();
				Map<String, Integer> dependencyPerMethod = new HashMap<>();
				
				complexityPerMethod.put("A", 10);
				dependencyPerMethod.put("A", 2);
				
				complexityPerMethod.put("B", 1);
				dependencyPerMethod.put("B", 1);
	
				complexityPerMethod.put("C", 0);
				dependencyPerMethod.put("C", 2);
	
				ClassMetrics cm = new ClassMetrics(200, "0", complexityPerMethod, dependencyPerMethod);
				firstCommit.add(cm);
			}
			
			metrics.add(firstCommit);
		}
		
		
		//Second commit
		{
			List<ClassMetrics> secondCommit = new ArrayList<>();
			
			//second commit, class 1
			{
				Map<String, Integer> complexityPerMethod = new HashMap<>();
				Map<String, Integer> dependencyPerMethod = new HashMap<>();
				
				complexityPerMethod.put("A", 2);
				dependencyPerMethod.put("A", 0);
				
				complexityPerMethod.put("B", 2);
				dependencyPerMethod.put("B", 0);
				
				ClassMetrics cm = new ClassMetrics(150, "0", complexityPerMethod, dependencyPerMethod);
				
				secondCommit.add(cm);
			}
			
			//second commit, class 2
			{
				Map<String, Integer> complexityPerMethod = new HashMap<>();
				Map<String, Integer> dependencyPerMethod = new HashMap<>();
				
				complexityPerMethod.put("A", 1);
				dependencyPerMethod.put("A", 0);
				
				complexityPerMethod.put("B", 1);
				dependencyPerMethod.put("B", 0);
	
				ClassMetrics cm = new ClassMetrics(240, "0", complexityPerMethod, dependencyPerMethod);
				secondCommit.add(cm);
			}

			//second commit, class 3
			{
				Map<String, Integer> complexityPerMethod = new HashMap<>();
				Map<String, Integer> dependencyPerMethod = new HashMap<>();
				
				complexityPerMethod.put("A", 1);
				dependencyPerMethod.put("A", 1);
				
				complexityPerMethod.put("B", 1);
				dependencyPerMethod.put("B", 1);
	
				ClassMetrics cm = new ClassMetrics(80, "0", complexityPerMethod, dependencyPerMethod);
				secondCommit.add(cm);
			}
						
			metrics.add(secondCommit);
		}
		
		return metrics;
	}
	
}
