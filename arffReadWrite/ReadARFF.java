package arffReadWrite;

import java.io.File;
import java.io.IOException;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.Loader;

public class ReadARFF {
	public Instances data;
	
	ArffLoader loader;
		
	public Instances readOneFile(File name){
		try {
			loader = new ArffLoader();
			loader.setSource(name);
			
			loader.setRetrieval(Loader.BATCH);
			data = loader.getDataSet();
			data.setClassIndex(data.numAttributes()-1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}finally{}
		return data;
	}
	
	public Instance getNextInstance(){
		Instance i=null;
		try {
			loader.setRetrieval(Loader.INCREMENTAL);
			
			if(loader!=null)
				i = loader.getNextInstance(data);
		
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		finally{}
		return i;
	}
}
