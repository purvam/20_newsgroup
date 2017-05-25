package preProcessAttributes;

import java.util.List;
import java.util.Map;

import weka.core.DenseInstance;
import weka.core.Instance;

public class CreateSingleInstance {

	public Instance inst;
	public List<String> stringList;
	public double[] missingValues;

	public CreateSingleInstance(List<String> sList, int size) {
		stringList = sList;
		missingValues = new double[size];
		
		inst = new DenseInstance(size);
		inst.replaceMissingValues(missingValues);
	}
	

	public CreateSingleInstance(List<String> sList) {
		stringList = sList;
		missingValues = new double[stringList.size()];
		
		inst = new DenseInstance(stringList.size());
		inst.replaceMissingValues(missingValues);
	}

	public void setValue(Map<String, Integer> map){
		int wordIndex=-1;
		
		for(String word: map.keySet()){
			wordIndex=stringList.indexOf(word);
			
			if(wordIndex != -1)
				inst.setValue(wordIndex, map.get(word));
		}
	}
	
	public Instance getInstance(){
		return inst;
	}
}
