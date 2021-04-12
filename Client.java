/* CS4850 Project - Client
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 4, 2021
 * Program Desciption:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
	public static void main(String[] args) {

        // Configuration Variables
        String hostname = "localhost";
        int portnumber = 4999;

        // Variables
        Socket socket = null;
        InputStreamReader consoleInputStream;
        BufferedReader consoleReader;

        // Create Socket Connection w/ Server
        try {
            socket = new Socket(hostname, portnumber);
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to create socket with host \"%s\" on port \"%d\"\n", hostname, portnumber);
            return;
        }
        
        // Main Loop
        for(int i = 0; i < 10; i++) {
        	try {
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println(getInputFromConsole());
                writer.flush();
            } catch (IOException exception) {
                System.err.printf("[Error] Failed to send message!\n");            
                return;
            }
        }
        
        // Close Connection
        try {
        	socket.close();
        } catch (IOException exception) {
        	System.err.printf("[Error] Failed to Close Socket!\n");
        }
        
    }
	
	public static String getInputFromConsole() throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String result = reader.readLine();
		return result;
	}
	
}