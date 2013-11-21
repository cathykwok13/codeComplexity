package proj.tools.complexity;

import java.util.List;

public class CouplingCalculator {
	
	static int getCouplingCalc(String method, List<String> classes) {
		
		int count = 0;
		int i;
		for(i = 0; i < classes.size(); i++) {
			if(method.contains(classes.get(i) + " "))
				count = count + 1;
		}
		return count;
	}
}