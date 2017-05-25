package useClassifier;

import java.util.Random;

import weka.classifiers.AbstractClassifier;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayesMultinomial;
import weka.classifiers.bayes.NaiveBayesMultinomialUpdateable;
import weka.core.Instances;

public class Classify {
	//NaivesBayesSimple
	public Classifier classifier;
	public int NUM_FOLDS;
	Random random;
	public Instances data;
	
	
	public Classify(Instances dataIn){
		classifier = new NaiveBayesMultinomial();
		NUM_FOLDS=10;
		random= new Random();
		data = dataIn;
		
	}
	
	public void crossValidateModel(){
		double totalError=0, avgError=0;
		
		Evaluation eval = null;
		Instances trainData, testData = null;
		data.randomize(random);
		
		if(data.classAttribute().isNominal()){
			data.stratify(NUM_FOLDS);
		}
		
		// Do the folds
		  for (int i = 0; i < NUM_FOLDS; i++) {
		    trainData = data.trainCV(NUM_FOLDS, i, random);
		    //setPriors(train);
		    Classifier copiedClassifier;
			try {
				copiedClassifier = AbstractClassifier.makeCopy(classifier);

				copiedClassifier.buildClassifier(trainData);
				
				//copiedClassifier = (NaiveBayesMultinomialUpdateable) weka.core.SerializationHelper.read("model/trained.model");
				
				testData = data.testCV(NUM_FOLDS, i);

				eval = new Evaluation(trainData);
			
				//first supply the classifier
				//then the training data
				//number of folds
				//random seed
				eval.evaluateModel(copiedClassifier, testData);
				
				totalError+=eval.pctIncorrect();
				System.out.println("Done fold" + NUM_FOLDS);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{}
		  }
		  
		  avgError = totalError/ NUM_FOLDS;
		  System.out.println("Avg Error:" + avgError);
	}
}
