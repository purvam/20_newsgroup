package useClassifier;

import java.io.File;

import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

public class UpdatableClassify {
	
	public NaiveBayesMultinomialUpdateable classifier;
	public Instances data;
	public ArffLoader loader;
	public File directory;
	public boolean upToDate;

	public void updateclassifier(){
		classifier = new NaiveBayesMultinomialUpdateable();
		loader = new ArffLoader();
		directory = new File("arffFiles");
		upToDate = false;
		letsReadFile(directory);
		
		try {
			File dir = new File("model");
			if(!(dir.isDirectory()))
				dir.mkdir();

			weka.core.SerializationHelper.write("model/trained.model", classifier);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public double useClassifier(Instance instance){
		try {
			classifier =  (NaiveBayesMultinomialUpdateable) weka.core.SerializationHelper.read("model/trained.model");
			
			return classifier.classifyInstance(instance);
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return 0;
	}
	
	public void readOneFileTrainModel(File name){
		try {
			loader.setFile(name);
			
			data = loader.getStructure();
			data.setClassIndex(data.numAttributes()-1);
			
			if(!upToDate){
				classifier.buildClassifier(data);
				upToDate = true;
			}
			else{
				Instance current;
				 while ((current = loader.getNextInstance(data)) != null){
				   classifier.updateClassifier(current);
				   //System.out.println(current.classIndex());
				 }
			}
			 
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}
	
	public void letsReadFile(File directory){
		System.out.println(directory.getName());

		if(directory.isDirectory()){
	        for (File fileOrFolder : directory.listFiles()) {
               	 letsReadFile(fileOrFolder); 
	        }
		}
		else {
			readOneFileTrainModel(directory);
		}
	}


}
