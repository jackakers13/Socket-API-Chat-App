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
		    StringBuilder in = new StringBuilder(reader.nextLine());
		    if(!in.toString().equals("")) {
		    	String trimmed = in.substring(1, in.length()-1);
		    	StringTokenizer tokenizer = new StringTokenizer(trimmed, ", ");
		    	System.out.println("[DEBUG] trimmed = " + trimmed);
		    	String username = tokenizer.nextToken();
		    	String password = tokenizer.nextToken();
		    	System.out.println("[DEBUG] username = " + username);
		    	System.out.println("[DEBUG] password = " + password);
			    users.put(username, password);
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
	    	  writer.write(String.format("(%s, %s)\n", user, users.get(user)));
	      }
	      writer.close();
		} catch (IOException exception) {
		  System.out.printf("[Error] Unable to Write %s!\n", fileName);
		}
	}

}
