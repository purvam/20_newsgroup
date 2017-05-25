package fileReading;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import stopWordFile.StopWordList;

public class ReadFile {
	public static BufferedReader br;
	public Map<String,Integer> words;
	
	public ReadFile(File inputFile){
		String line=null;
		
		words = new HashMap<String,Integer>();

		try {
			br= new BufferedReader(new FileReader(inputFile));
			line = br.readLine();
			
			while(line!=null && line.length() !=0){	//ignore till new line found
				if(line.startsWith("Subject:"))		splitLine(line, "subject",words);	
				//if(line.startsWith("Newsgroups:"))	splitLine(line,"newsgroups", words);
				
				line = br.readLine();
			}
			
			line = br.readLine();
			while(line!=null){
				splitLine(line,"", words);
				line = br.readLine();
			}
			br.close();
			//System.out.println("Read file!");
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
		finally{}
		//System.out.println(words);
	}

	public Map<String, Integer> getWordCountMap(){
		return words;
	}
	
	public void splitLine(String line, String ignore, Map<String,Integer> words){
		String tempWord[];
		
		line =line.replaceAll("[^a-zA-Z\\s]", "").toLowerCase();
		tempWord =line.split(" ");
		
		for(String temp: tempWord){
			temp = temp.replaceAll("\\s+", "");
			
			if(temp.length()==0 || temp.length()>12)	//most english words smaller than 12
				continue;
			
			if(!(temp.equals(ignore))&& (!StopWordList.list.contains(temp))){
				
				if(!words.containsKey(temp))		words.put(temp,1);
				else		words.replace(temp,words.get(temp)+1);
			}
		}

	}

}

