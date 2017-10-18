package assignment2;

import java.net.Socket;

public class ServerResponseThread extends Thread {

	private final Socket socket;
	
	public ServerResponseThread(Socket socket){
		this.socket = socket;
		
	}
	
	@Override
	public void run() {
		System.out.println("hello!");
		
	}

}
