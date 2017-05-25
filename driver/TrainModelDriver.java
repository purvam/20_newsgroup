package driver;

import java.io.BufferedReader;

import useClassifier.UpdatableClassify;

public class TrainModelDriver {
	
	public static BufferedReader br;
	
	public static String SWInputFile, dirPath; 
	
	public static void main(String args[]){
		System.out.println("Starts here!\nTraining the classifier from ARFF files...\n");
		
		try {

			/*
			 * to cross validate
			 * 
			ReadARFF ra = new ReadARFF();
			Classify cl= new Classify(ra.readOneFile(new File("arffFiles/sci.crypt.arff")));
			cl.crossValidateModel();

*/
						
			UpdatableClassify ucl = new UpdatableClassify();
			ucl.updateclassifier();

			System.out.println("\nClassifier is trained and serialized!!!");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	 
	}
}
