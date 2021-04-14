package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

public class AccountManager {
	
	static HashMap<String, String> users = new HashMap<>();
	static String fileName = "users.txt";
	
	public static void loadFromFile() {
		users.clear();
		try {
		  File file = new File(fileName);
		  if(!file.exists())
			  saveToFile();
		  Scanner reader = new Scanner(file);
		  while (reader.hasNextLine()) {
		    String in = reader.nextLine();
		    if(!in.contentEquals("")) {
		    	StringTokenizer tokenizer = new StringTokenizer(in, "\t");
			    users.put(tokenizer.nextToken(), tokenizer.nextToken());	
		    }
		  }
		  reader.close();
		} catch (FileNotFoundException exception) {
		  System.out.printf("[Error] Unable to Read %s!\n", fileName);
		}
	}
	
	public static void saveToFile() {
		try {
		  File file = new File(fileName);
		  file.createNewFile(); // Safe to run, doesn't override existing file.
		  FileWriter writer = new FileWriter(fileName);
	      for(String user : users.keySet()) {
	    	  writer.write(String.format("%s\t%s\n", user, users.get(user)));
	      }
	      writer.close();
		} catch (IOException exception) {
		  System.out.printf("[Error] Unable to Write %s!\n", fileName);
		}
	}

}
