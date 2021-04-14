package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientListenerThread extends Thread {
	
    Socket socket = null;
    InputStreamReader inputStream = null;
    BufferedReader reader = null;
    
    public ClientListenerThread(Socket socket) {
    	try {
    		this.socket = socket;
			this.inputStream = new InputStreamReader(socket.getInputStream());
			this.reader = new BufferedReader(inputStream);
		} catch (IOException e) {
			System.err.printf("[Error] Unable to construct ClientListenerThread\n");
		}
    }
	
	public void run() {
		while(true) {
			try {
				while(!reader.ready()) {}
				System.out.println(reader.readLine());
			} catch (IOException e) {
				System.err.printf("[Error] Caught IOException in ClientListenerThread\n");
			}
		}
	}

}
