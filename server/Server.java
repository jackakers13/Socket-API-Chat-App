package server;
/* CS4850 Project - Server
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 12, 2021
 * Program Description:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class Server {
    public static void main(String[] args) {

        // Configuration Variables
        int portnumber = 12074;

        // Variables
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader inputStream = null;
        BufferedReader reader = null;

        // Accept Socket Connection from Client
        try {
            serverSocket = new ServerSocket(portnumber);
            socket = serverSocket.accept();
            inputStream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStream);
            System.out.printf("Connected to Client %s\n", socket.getRemoteSocketAddress().toString());
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to create socket on port \"%d\"\n", portnumber);
            return;
        }
        
        // Main Loop
        mainloop:
        while(true) {
        	try {
				while(!reader.ready()) {}
				String in = reader.readLine();
				System.out.printf("Received \"%s\" from client.\n", in);
				StringTokenizer tokenizer = new StringTokenizer(in);
				switch(tokenizer.nextToken()) {
					case "shutdown": // Not in the spec, here for convenience.
						break mainloop;
					case "login":
						break;
					case "newuser":
						break;
					case "send":
						break;
					case "logout":
						break;
					default:
						break;
				}
			} catch (IOException e) {
				System.err.printf("[Error] Caught IOException in Main Loop\n");
			}	
        }
        
        // Close Connection
        try {
        	serverSocket.close();
        } catch (IOException exception) {
        	System.err.printf("[Error] Failed to Close Socket!\n");
        }
        
        System.out.printf("Server Shutting Down...\n");
        
    }
}