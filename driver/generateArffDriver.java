package driver;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import fileReading.ReadDirectory;
import preProcessAttributes.CreateInstances;
import stopWordFile.StopWordList;

public class generateArffDriver {
	public static BufferedReader br;
	
	public static String SWInputFile, dirPath; 
	
	public static void main(String args[]){
		System.out.println("Starts here!");
		
		try {

			br = new BufferedReader(new InputStreamReader(System.in));
			
			//stop words to ignore data file
			System.out.println("\nEnter stopwords data file path:");
			SWInputFile = br.readLine();
	
			StopWordList swList = new StopWordList(SWInputFile);
			
			System.out.println("\nEnter training data file path:");
			dirPath = br.readLine();
			
			ReadDirectory rd= new ReadDirectory(dirPath);
			System.out.println("\nAll Files read.\nProceeding to convert in ARFF format...");

			CreateInstances csi = new CreateInstances(rd);
			System.out.println("\nTraining input data is written in ARFF files!");
			//System.out.println("Done");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	 
	}

}
