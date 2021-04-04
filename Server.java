/* CS4850 Project - Server
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 4, 2021
 * Program Desciption:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        int portnumber = 4999;

        ServerSocket serverSocket;
        Socket socket;

        try {
            serverSocket = new ServerSocket(4999);
            socket = serverSocket.accept();
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to create socket on port \"%d\"", portnumber);
        }

        System.out.println("Client Connected!");
        
    }
}