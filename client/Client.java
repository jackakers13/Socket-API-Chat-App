package client;
/* CS4850 Project - Client
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 12, 2021
 * Program Description:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.StringTokenizer;

import server.AccountManager;

public class Client {
	public static void main(String[] args) {

        // Configuration Variables
        String hostname = "localhost";
        int portnumber = 12074;

        // Create Socket Connection w/ Server
        ClientConnection session = new ClientConnection();
        session.connect(hostname, portnumber);
        
        // Start Listener Thread
        ClientListenerThread listenerThread = new ClientListenerThread(session.getSocket());
        listenerThread.start();
        
        // Main Loop
        mainloop:
        while(true) {
        	try {
                PrintWriter writer = new PrintWriter(session.getSocket().getOutputStream());
                String input = getInputFromConsole();
                StringTokenizer tokenizer = new StringTokenizer(input);
                String command = tokenizer.nextToken();
                switch(command) {
	    			case "login":
	    				if(tokenizer.countTokens() == 2) {
	    					writer.println(input);
	    	                writer.flush();
	    				}
	    				else {
    						System.err.println("[ERROR] Invalid Parameters for Command \"login\"");
    					}
	    				break;
	    			case "newuser":
	    				if(tokenizer.countTokens() == 2) {
	    					writer.println(input);
	    	                writer.flush();
	    				}
	    				else {
    						System.err.println("[ERROR] Invalid Parameters for Command \"newuser\"");
    					}
	    				break;
	    			case "send":
	    				writer.println(input);
    	                writer.flush();
	    				break;
	    			case "logout":
	    				writer.println(input);
    	                writer.flush();
	    				break mainloop;
	    			default:
	    				System.err.println("[ERROR] Invalid Command!");
	    				break;
                }
            } catch (IOException exception) {
                System.err.printf("[Error] Failed to send message!\n");            
                return;
            }
        }
        
        // Close Connection
        listenerThread.stop();
        session.close();     
        System.out.printf("Client Shutting Down...\n");
        
    }
	
	public static String getInputFromConsole() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String result = reader.readLine();
		return result;
	}
	
}