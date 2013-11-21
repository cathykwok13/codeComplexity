package proj.tools.complexity;

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
		
		//case 1: void || 1 return
		if(s.contains("return") ){
			System.out.println("METHOD RETURN");
			return 1;
		}
		else if(s.contains("void")){
			System.out.println("METHOD VOID");
			return 1;
		}
		
		
	System.out.println("WHAT IS THIS "+ s);

		return 0;
	}
	

}
