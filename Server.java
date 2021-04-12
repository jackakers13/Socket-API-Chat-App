/* CS4850 Project - Server
 * Student Name: Jack Akers (jdapm8, 12562074)
 * Date: April 4, 2021
 * Program Desciption:
 * A simple chat room service comprised of a client and a server that utilize the socket API.
 * Implemented using Java 8; built and tested on Windows 10.
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        // Configuration Variables
        int portnumber = 4999;

        // Variables
        ServerSocket serverSocket = null;
        Socket socket = null;
        InputStreamReader inputStream = null;
        BufferedReader reader = null;

        // Accept Socket Connection from Client
        try {
            serverSocket = new ServerSocket(4999);
            socket = serverSocket.accept();
            inputStream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inputStream);
            System.out.println("Client Connected!");
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to create socket on port \"%d\"\n", portnumber);
        }

        // Recieve Message
        try {
            System.out.printf("Recieved \"%s\" from client.\n", reader.readLine());
        } catch (IOException exception) {
            System.err.printf("[Error] Failed to read from BufferedReader!\n");
        }

    }
}