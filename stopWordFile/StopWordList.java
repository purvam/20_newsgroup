package stopWordFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;
import java.util.Set;

public class StopWordList {
	public static Set<String> list= new HashSet<String>();
	
	public static BufferedReader br;
	
	public StopWordList(String inputFile){
		String word=null;
		try {
			br= new BufferedReader(new FileReader(inputFile));
			word = br.readLine();
			while(word!=null){
				list.add(word);
				word = br.readLine();
			}
			br.close();
			
			System.out.println("Stop words are populated!");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		finally{}
	}
}
