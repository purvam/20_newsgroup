package readWriteAttributes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import weka.core.Attribute;

public class ReadAttributes {
	public static BufferedReader br;
	public static List<String> classList;
	public static List<String> stringList;
	public static List<Attribute> attributeList;
	
	public ReadAttributes(){
		readClassList();
		readStringList();		
	}
	
	public List<Attribute> getAttributeList(){

		attributeList = new ArrayList<Attribute>();
		for(String key : stringList){
			attributeList.add(new Attribute(key));
		}

		attributeList.add(new Attribute("Class_Label",classList));

		return attributeList;
	}
	
	
	public List<String> getClassList(){
		return classList;
	}
	
	public List<String> getStringList(){
		return stringList;
	}
	
	private void readClassList(){
		String line=null;
		
		classList = new ArrayList<String>();

		try {
			br= new BufferedReader(new FileReader(new File("AttributeFile","ClassList.txt")));
			
			line = br.readLine();
			
			while(line!=null && line.length() !=0){	//ignore till new line found
				classList.add(line);
				line = br.readLine();
			}
			 if(br!=null){
				 br.close();
				 System.out.println("Class read from file!");
			 }
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}

	private void readStringList(){
		String line=null;
		
		stringList = new ArrayList<String>();

		try {
			br= new BufferedReader(new FileReader(new File("AttributeFile","AttributeStringList.txt")));
			
			line = br.readLine();
			
			while(line!=null && line.length() !=0){	//ignore till new line found
				stringList.add(line);
				line = br.readLine();
			}
			 if(br!=null){
				 br.close();
				 System.out.println("Attributes String read from file!");
			 }
			
		}catch(Exception e){
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}

}
