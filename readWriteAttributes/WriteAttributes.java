package readWriteAttributes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class WriteAttributes {
	
	public static void writeClassList(List<String> clist){
		BufferedWriter bw=null;
		File dir = new File("AttributeFile");
		if(!(dir.isDirectory()))
			dir.mkdir();

		try {
			bw = new BufferedWriter(new FileWriter(new File(dir.toString(),"ClassList.txt")));
			for(String s: clist){
				bw.write(s +"\n");
			}
			if(bw!=null){
				bw.close();
				System.out.println("Classes written in file!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}

	public static void writeStringList(List<String> alist){
		BufferedWriter bw=null;
		File dir = new File("AttributeFile");
		if(!(dir.isDirectory()))
			dir.mkdir();

		try {
			bw = new BufferedWriter(new FileWriter(new File(dir.toString(),"AttributeStringList.txt")));
			for(String s: alist){
				bw.write(s +"\n");
			}
			if(bw!=null){
				bw.close();
				System.out.println("Attributes String written in file!");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}finally{}
	}

}
