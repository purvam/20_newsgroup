package driver;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import arffReadWrite.ReadARFF;
import useClassifier.UpdatableClassify;
import weka.core.Instances;

public class PredictDriver {
	
	public static void main(String args[]){
		System.out.println("Starts here!\nPredicting from the given test files...");
		
		try {
			
			ReadARFF ra = new ReadARFF();
			Instances ina = ra.readOneFile(new File("testInput.arff"));
			
			UpdatableClassify ucl = new UpdatableClassify();
			
			Enumeration<Object> classValues = ina.classAttribute().enumerateValues();
			List<String> list = new ArrayList<String>();
			
			while(classValues.hasMoreElements())	list.add((String)classValues.nextElement());
			
			double missing = 0;
			
			for(int i = 0; i < ina.numInstances(); ++i){
				double pred = ucl.useClassifier(ina.instance(i));
//				System.out.println("class: " + ina.classAttribute().value((int)pred) + " " +pred);
				
				if(ina.instance(i).classValue() != pred){
					++missing;
				}
			}
			
			System.out.println("\nPredicting done! \nAverage Error for the test input files: "+ (missing/ina.numInstances()));

			/*
			 * for individual instance 
			 * 
			br = new BufferedReader(new InputStreamReader(System.in));
			
			//stop words to ignore data file
			System.out.println("Enter stopwords data file path:");
			SWInputFile = br.readLine();
	
			StopWordList swList = new StopWordList(SWInputFile);
			
			System.out.println("Enter training data file path:");
			dirPath = br.readLine();

			ReadFile rf= new ReadFile(new File(dirPath));
			System.out.println("Done Read");
			
			
			ReadAttributes ra = new ReadAttributes();
			ArrayList<Attribute> attriList = (ArrayList<Attribute>) ra.getAttributeList();
			ArrayList<String> classA = (ArrayList<String>) ra.getClassList();
					
			CreateSingleInstance csi = new CreateSingleInstance(ra.getStringList());
			Instances dataset = new Instances("TestInstances", attriList, 1);
			dataset.add(csi.getInstance());
			dataset.setClassIndex(dataset.numAttributes() - 1);
			
			System.out.println(rf.getWordCountMap());
			csi.setValue(rf.getWordCountMap());

			
			double pred = ucl.useClassifier(dataset.firstInstance());
			
			
			String c = Utils.doubleToString(pred,5);
			System.out.println("class: " + dataset.classAttribute().value((int)pred) + " " +pred);
			System.out.println(Utils.doubleToString(pred,2));
			
			System.out.println("\n\n\n");
			double [] preds = ucl.classifier.distributionForInstance(dataset.firstInstance());
			for(int i=0;i<preds.length;i++)
				System.out.println(classA.get(i) + " "+ Utils.doubleToString(preds[i],10));
*/			

			
			System.out.println("Done");
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}
}
