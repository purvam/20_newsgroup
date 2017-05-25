package preProcessAttributes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fileReading.ReadDirectory;
import readWriteAttributes.WriteAttributes;
import weka.core.Attribute;

public class ListOfKeys {
	
	public static List<String> alist = null;
	public static List<Attribute> attributes = null;
	public static ArrayList<String> classID = null;
	
	public static List<String> getStringList(ReadDirectory rd){		
		Map<String,Map<String,Map<String, Integer> > > matrix = rd.getMatrixMap();

		Set<String> set = new HashSet<String>();
		alist = new ArrayList<String>();
		classID = new ArrayList<String>();;
		
		for(String key: matrix.keySet()){
			for(String file : matrix.get(key).keySet()){
				for(String word : matrix.get(key).get(file).keySet())
					set.add(word);
			}
			classID.add(key);	//class label in attribute arraylist
		}
		
		for(String key: set){
			alist.add(key);
		}
		
		WriteAttributes.writeStringList(alist);	//future use
		WriteAttributes.writeClassList(classID);	//future use

		return alist;
	}
	
	public static List<Attribute> getAttributeList(ReadDirectory rd){
		
		if(alist==null || classID == null)		getStringList(rd);
		
		attributes = new ArrayList<Attribute>();
		for(String key : alist){
			attributes.add(new Attribute(key));
		}

		attributes.add(new Attribute("Class_Label",classID));
		
		return attributes;
	}
	
}
