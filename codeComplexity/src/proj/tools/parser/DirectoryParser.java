package proj.tools.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DirectoryParser takes a directory path and parses through the directories to
 * get all the paths to the java files
 * 
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

	public List<String> getFolders(String path) throws FileNotFoundException {
		File bugReport = new File(path + "/bugReport.txt");
		String[] commitId = BugReportParser.readCommitId(bugReport);
		
		String[] folderNamesInArray = new String[commitId.length];

		for (int i = 0; i < projDirContents.length; i++) {
			int index = getCommitIndex(commitId, projDirContents[i].getName());
			if (projDirContents[i].isDirectory() && index >= 0 ) {
				folderNamesInArray[index] = projDirContents[i].getPath();
				//System.out.println(projDirContents[i].getName());
			}
		}
		//folderNames = 
		return Arrays.asList(folderNamesInArray);
	}

	private int getCommitIndex(String[] commitId, String name ){
		for(int i = 0; i<commitId.length ; i++){
			if(name.contains(commitId[i].trim()))
				return i;
		}
		return -1;
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
