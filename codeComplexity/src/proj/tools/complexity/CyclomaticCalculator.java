package proj.tools.complexity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to calculate the 
 * code cyclomatic complexity of a method
 * 
 * The complexity M is then defined as
 * 		M = E − N + 2P,
 * where
 * 		E = the number of edges of the graph,
 * 		N = the number of nodes of the graph,
 * 		P = the number of connected components (exit nodes). 
 * 
 * It can be shown that the cyclomatic complexity of any structured program with only one entrance point 
 * and one exit point is equal to the number of decision points (i.e., "if" statements or conditional loops) 
 * contained in that program plus one.
 * @author cathy
 *
 */


public class CyclomaticCalculator {
	
	
	static int getCyclomaticCalc(String s){
		
		//calculate how many exit nodes
		Pattern p = Pattern.compile("return");
		Matcher matcher = p.matcher(s);

		int count = 0;
		while(matcher.find())
			count++;
		
		if(count>1){
			//do the algo with >1 exit node
			return 0;
		}else{
			return getDecisionPoint(s) + 1;
		}
		
	}
	
	private static int getDecisionPoint(String s){
		

		Pattern ifP = Pattern.compile("if\\s?\\(");
		Pattern caseP = Pattern.compile("case\\s{1}[A-Za-z]+\\s?:");
		Matcher ifMatch = ifP.matcher(s);
		Matcher caseMatch = caseP.matcher(s);

		int count = 0;
		while(ifMatch.find())
			count++;
		while(caseMatch.find())
			count++;
	
		//System.out.println(count);		
		return count;
	}
	

}
