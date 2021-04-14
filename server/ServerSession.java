package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerSession extends Thread {
	
	// Variables
    Socket socket = null;
    InputStreamReader inputStream = null;
    OutputStreamWriter outputSteam = null;
    BufferedReader reader = null;
    PrintWriter writer = null;
    
    // Account Variables
    boolean loggedIn = false;
    String loggedInUser = null;
    
    public ServerSession(Socket socket, InputStreamReader inputStream, OutputStreamWriter outputStream) {
    	this.socket = socket;
    	this.inputStream = inputStream;
    	this.outputSteam = outputStream;
    	this.reader = new BufferedReader(inputStream);
    	this.writer = new PrintWriter(outputStream, true);
    	System.out.printf("Connected to Client %s\n", socket.toString());
    }
    
    public void run() {
        while(true) {
        	try {
	    		while(!reader.ready()) {}
	    		String in = reader.readLine();
	    		System.out.printf("Received \"%s\" from client.\n", in);
	    		StringTokenizer tokenizer = new StringTokenizer(in);
	    		String command = tokenizer.nextToken();
	    		System.out.printf("Attempting to execute command \"%s\".\n", command);
	    		switch(command) {
	    			case "login":
	    				if(!loggedIn) {
	    					if(tokenizer.countTokens() == 2) {
	    						String username = tokenizer.nextToken();
	    						String password = tokenizer.nextToken();
	    						AccountManager.loadFromFile();
	    		    			if(AccountManager.users.containsKey(username) && AccountManager.users.get(username).equals(password)) {
	    		    				loggedInUser = username;
	    		    				loggedIn = true;
	    		    				writer.println("[INFO] Login Successful!");
	    		    			}
	    		    			else {
		    						writer.println("[ERROR] Invalid Credentials!");
		    					}
	    					}
	    					else {
	    						writer.println("[ERROR] Invalid Parameters for Command \"login\"");
	    					}
	    				}
	    				else {
    						writer.println("[ERROR] Already logged in");
    					}
	    				break;
	    			case "newuser":
	    				if(!loggedIn) {
	    					if(tokenizer.countTokens() == 2) {
	    						String username = tokenizer.nextToken();
	    						String password = tokenizer.nextToken();
	    						AccountManager.loadFromFile();
	    		    			if(!AccountManager.users.containsKey(username)) {
	    		    				AccountManager.users.put(username, password);
	    		    				AccountManager.saveToFile();
	    		    				writer.println("[INFO] User Created Successfully!");
	    		    			}
	    		    			else {
		    						writer.println("[ERROR] That Username is Taken!");
		    					}
	    					}
	    					else {
	    						writer.println("[ERROR] Invalid Parameters for Command \"newuser\"");
	    					}
	    				}
	    				else {
    						writer.println("[ERROR] Cannot perform this action while logged in");
    					}
	    				break;
	    			case "send":
	    				if(loggedIn) {
	    					StringBuilder returnMessage = new StringBuilder();
	    					returnMessage.append("<"+loggedInUser+">");
	    					while(tokenizer.hasMoreTokens()) returnMessage.append(" " + tokenizer.nextToken());
	    					writer.println(returnMessage.toString());
	    				}
	    				else {
    						writer.println("[ERROR] Not Logged In!");
    					}
	    				break;
	    			case "logout":
	    				if(loggedIn) {
	    					loggedIn = false;
	    					loggedInUser = null;
	    					socket.close();
	    					return;
	    				}
	    				else {
    						writer.println("[ERROR] Not Logged In!");
    					}
	    				break;
	    			default:
	    				writer.println("[ERROR] Invalid Command!");
	    				break;
	    		}
	    	} catch (IOException e) {
	    		System.err.printf("[Error] Caught IOException in ServerSession Loop\n", this.getName());
	    	}	
        }
    }
    
}
