package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServerSession extends Thread {
	
	// Variables
    Socket socket = null;
    InputStreamReader inputStream = null;
    OutputStreamWriter outputSteam = null;
    BufferedReader reader = null;
    public ServerSession(Socket socket, InputStreamReader inputStream, OutputStreamWriter outputStream) {
    	this.socket = socket;
    	this.inputStream = inputStream;
    	this.outputSteam = outputStream;
    	this.reader = new BufferedReader(inputStream);
    	System.out.printf("Connected to Client %s\n", socket.toString());
    }
    
    public void run() {
        while(true) {
        	try {
	    		while(!reader.ready()) {}
	    		String in = reader.readLine();
	    		System.out.printf("Received \"%s\" from client.\n", in);
	    		StringTokenizer tokenizer = new StringTokenizer(in);
	    		switch(tokenizer.nextToken()) {
	    			case "login":
	    				break;
	    			case "newuser":
	    				break;
	    			case "send":
	    				break;
	    			case "logout":
	    				socket.close();
	    				return;
	    			default:
	    				break;
	    		}
	    	} catch (IOException e) {
	    		System.err.printf("[Error] Caught IOException in ServerSession Loop\n", this.getName());
	    	}	
        }
    }
    
}
