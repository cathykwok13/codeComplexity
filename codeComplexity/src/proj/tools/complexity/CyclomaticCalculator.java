package proj.tools.complexity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The purpose of this class is to calculate the code cyclomatic complexity of a
 * method
 * 
 * The complexity M is then defined as M = pi - s + 2
 * 
 * Where pi is the number of decision points (case,if,for) s is the number of
 * exit points
 * 
 * @author cathy
 * 
 */

public class CyclomaticCalculator {

	static int getCyclomaticCalc(String s) {
		return getDecisionPoint(s) - getExitPoints(s) + 2;
	}

	private static int getDecisionPoint(String s) {

		Pattern ifP = Pattern.compile("if\\s?\\(");
		Pattern caseP = Pattern.compile("case\\s{1}[A-Za-z]+\\s?:");
		Pattern forP = Pattern.compile("for\\s?\\(");
		Pattern whileP = Pattern.compile("while\\s?\\(");
		Matcher ifMatch = ifP.matcher(s);
		Matcher caseMatch = caseP.matcher(s);
		Matcher forMatch = forP.matcher(s);
		Matcher whileMatch = whileP.matcher(s);

		int count = 0;

		while (ifMatch.find() || caseMatch.find() || forMatch.find()
				|| whileMatch.find())
			count++;

		return count;
	}

	private static int getExitPoints(String s) {
		// calculate how many exit nodes
		Pattern p = Pattern.compile("return");
		Matcher matcher = p.matcher(s);

		int count = 0;
		while (matcher.find())
			count++;

		if (count == 0)
			return 1;
		return count;
	}

}
