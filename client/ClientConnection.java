package client;

import java.io.IOException;
import java.net.Socket;

public class ClientConnection {
	
	private boolean connected = false;
	private Socket socket = null;
	
	public void connect(String hostname, int portnumber) {
		while(!connected) {
	    	try {
	            socket = new Socket(hostname, portnumber);
	            connected = true;
	        } catch (IOException exception) {
	            System.err.printf("[Error] Failed to connect to %s:%d, trying again in 5 seconds...\n", hostname, portnumber);
	        }
	    }
	    System.out.printf("Connected to %s:%d.\n", hostname, portnumber);
	}
	
	public void close() {
		try {
        	socket.close();
        	connected = false;
        } catch (IOException exception) {
        	System.err.printf("[Error] Failed to Close Socket!\n");
        }
	}
	
	public boolean getConnected() {
		return connected;
	}
	
	public Socket getSocket() {
		return socket;
	}

}
