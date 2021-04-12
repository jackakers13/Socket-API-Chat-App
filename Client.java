/* CS4850 Project - Client
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 4, 2021
 * Program Desciption:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {

        // Configuration Variables
        String hostname = "localhost";
        int portnumber = 4999;

        // Variables
        Socket socket = null;

        // Create Socket Connection w/ Server
        try {
            socket = new Socket(hostname, portnumber);
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to create socket with host \"%s\" on port \"%d\"\n", hostname, portnumber);
            return;
        }

        // Send Message
        try {
            PrintWriter writer = new PrintWriter(socket.getOutputStream());
            writer.println("Hello, I'm a Client!");
            writer.flush();
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to send message!\n");
            return;
        }
        
    }
}