package server;
/* CS4850 Project - Server
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 12, 2021
 * Program Description:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        // Configuration Variables
        int portnumber = 12074;
        
        // Variables
        ServerSocket serverSocket = null;
        
        // Create Server Socket
        try {
        	serverSocket = new ServerSocket(portnumber);
        } catch (IOException exception) {
        	System.err.printf("[Error] Failed to create server socket on port \"%d\"\n", portnumber);
            return;
        }
        
        // Accept Client Connection
        while(true) {
        	Socket socket = null;
            InputStreamReader inputStream = null;
            OutputStreamWriter outputStream = null;
            Thread thread = null;

            try {
                socket = serverSocket.accept();
                inputStream = new InputStreamReader(socket.getInputStream());
                outputStream = new OutputStreamWriter(socket.getOutputStream());
            } catch (IOException exception) {
                System.err.printf("[Error] Failed to create socket on port \"%d\"\n", portnumber);
                break;
            }
            
            thread = new ServerSession(socket, inputStream, outputStream);
            thread.start();
            
            while(thread.isAlive()) {} // Limit to only 1 client at a time for V1
        }
        
        
        // Close Server Socket
        try {
        	serverSocket.close();
        } catch (IOException exception) {
        	System.err.printf("[Error] Failed to Close Server Socket!\n");
        }
        
        System.out.printf("Server Shutting Down...\n");
        
    }
}