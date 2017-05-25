package preProcessAttributes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import arffReadWrite.WriteARFF;
import fileReading.ReadDirectory;
import weka.core.Attribute;
import weka.core.Instances;

public class CreateInstances {

	Instances allData;
	
	public List<String> stringList;
	public ArrayList<Attribute> attributelist;
	public Map<String,Map<String,Map<String, Integer> > > matrix;
	
	public WriteARFF wf;
	
	public double[] missingValues;
	
	public CreateInstances(ReadDirectory rd){
		stringList= (ArrayList<String>) ListOfKeys.getStringList(rd);
		attributelist = (ArrayList<Attribute>) ListOfKeys.getAttributeList(rd);
		matrix = rd.getMatrixMap();
		
		missingValues = new double[attributelist.size()];
		
		updateInstances();
	}
	
	public void updateInstances(){
		String classID="";
		String fileID="";

		Iterator<Entry<String, Map<String, Map<String, Integer>>>> classIt = matrix.entrySet().iterator();
		while (classIt.hasNext()) {
			
			allData = new Instances("ALL_DATA",attributelist, 1000);
			allData.setClassIndex(attributelist.size()-1);
			
	        @SuppressWarnings("rawtypes")
			Map.Entry classFilePair = (Map.Entry)classIt.next();

	        classID = classFilePair.getKey().toString();
			System.out.println("Processing " + classID);

			@SuppressWarnings("unchecked")
			Iterator<Entry<String, Map<String, Integer>>> fileIt = ((Map<String,Map<String,Integer>>) classFilePair.getValue()).entrySet().iterator();
		    while (fileIt.hasNext()) {
		    	
		        @SuppressWarnings("rawtypes")
				Map.Entry fileWordPair = (Map.Entry)fileIt.next();
		        
		        fileID = fileWordPair.getKey().toString();

		        CreateSingleInstance csi = new CreateSingleInstance(stringList, attributelist.size());
		        csi.getInstance().setDataset(allData);
				
		        csi.setValue(matrix.get(classID).get(fileID));
		        
				//for class label
		        csi.getInstance().setValue(attributelist.size()-1, classID);
	
				allData.add(csi.getInstance());
				
				fileIt.remove();
		    }
		    
			System.out.println("Writing " + classID);
			
			wf = new WriteARFF(allData, classID);
		    wf.writeBunch();
	        classIt.remove();
	        System.gc();
	    }
	}
	
	public Instances getInstaces(){
		return allData;
	}
}
