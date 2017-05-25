package arffReadWrite;

import java.io.File;
import java.io.IOException;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.Saver;

public class WriteARFF {
	public Instances data;
	
	ArffSaver saver;
	
	public WriteARFF(Instances dataIn, String name) {
		data = dataIn;
		saver = new ArffSaver();
		saver.setInstances(data);
		
		File dir = new File("arffFiles");
		if(!(dir.isDirectory()))
			dir.mkdir();
		
		name = name + ".arff";
		
		try {
			saver.setFile(new File(dir.toString(),name));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void writeIncrement(Instance inst){
		try {
			saver.setRetrieval(Saver.INCREMENTAL);
			saver.writeIncremental(inst);;
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void writeBunch(){
		try {
			saver.setRetrieval(Saver.BATCH);
			saver.writeBatch();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

}
