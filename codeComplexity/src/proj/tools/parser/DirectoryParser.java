package proj.tools.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * DirectoryParser takes a directory path and parses through
 * the directories to get all the paths to the java files 
 * @author cathy
 *
 */
public class DirectoryParser {

	File[] projDirContents;
	List<String> javaList = new ArrayList<String>();
	List<String> classList = new ArrayList<String>();

	public DirectoryParser(String path) {
		projDirContents = new File(path).listFiles();
	}

	public List<String> getJavaFiles() {
		getJavaFiles(projDirContents);
		return javaList;
	}
	
	public List<String> getClassList() {
		getJavaFiles(projDirContents);
		return classList;
	}
	
	private void getJavaFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				getJavaFiles(file.listFiles()); // Calls same method again.
			} else if (file.getName().contains("java")) {
				javaList.add(file.getAbsolutePath());
				String s = file.getName();
				s = s.replace(".java", "");
				classList.add(s);
			}
		}

	}
}
