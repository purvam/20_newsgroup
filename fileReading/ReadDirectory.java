package fileReading;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ReadDirectory {
	public Map<String,Map<String,Map<String, Integer> > > matrix;
	
	public File targetDir;
	
	public ReadDirectory(String targetDirIn){
		File targetDir = new File(targetDirIn);
		
		matrix = new HashMap<String,Map<String,Map<String, Integer> > >();
		
		letsReadFile(targetDir);
	}
	
	public void letsReadFile(File directory){
		if(directory.isDirectory()){
	        for (File fileOrFolder : directory.listFiles()) {
	        	 if(fileOrFolder.isDirectory()){
	                 for (File singleFileOrFolder : fileOrFolder.listFiles()){
	                	 letsReadFile(singleFileOrFolder); 
	                 }
	        	 }
	        }
		}
		else {
			 ReadFile rf = new ReadFile(directory);
			 
			 String fileName=directory.toString();
			 String folderName=null;
			 String [] names=fileName.split("\\\\");
				 int length= names.length;
				 
				 fileName= names[length-1];
				 folderName=names[length-2];
				 
				 //System.out.println(fileName + " " + folderName);
				 if(!matrix.containsKey(folderName)){
					 matrix.put(folderName, new HashMap<>());
				 }
				 matrix.get(folderName).put(fileName, rf.getWordCountMap());
			}

	}
	
	public Map<String, Map<String, Map<String, Integer>>> getMatrixMap(){
		return matrix;
	}
}
