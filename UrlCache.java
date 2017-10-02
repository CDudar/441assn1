package assignment1;

import java.io.DataOutputStream;

/**
 * UrlCache Class
 * 
 *
 */

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;


public class UrlCache {

	HashMap<String, String> catalogue;
	
	
    /**
     * Default constructor to initialize data structures used for caching/etc
	 * If the cache already exists then load it. If any errors then throw runtime exception.
	 *
     * @throws IOException if encounters any errors/exceptions
     */
	public UrlCache() throws IOException {
		catalogue = new HashMap<String, String>();
	}
	
    /**
     * Downloads the object specified by the parameter url if the local copy is out of date.
	 *
     * @param url	URL of the object to be downloaded. It is a fully qualified URL.
     * @throws IOException if encounters any errors/exceptions
     */
	public void getObject(String url) throws IOException {
		
		String hostName;
		String pathName;
		int portNumber;
		boolean portNumberGiven = false;
		
		String s, tmp;
		Scanner inputStream;
		PrintWriter outputStream;
		Scanner userinput;
		
		hostName = url.substring(0, url.indexOf("/"));
		pathName = url.substring(url.indexOf("/"));
		
		if(url.indexOf(":") != -1) {
			portNumber = Integer.parseInt(url.substring(url.indexOf(":") + 1, url.indexOf("/")));
			portNumberGiven = true;
			System.out.println(portNumber);
			
		}
					
		System.out.println(hostName);
		System.out.println(pathName);


		

		
		/**
		
		
		try {
			// connects to port server app listening at port 8888 in the same
			// machine
			Socket socket = new Socket(hostName, 8888);

			// Create necessary streams
			outputStream = new PrintWriter(new DataOutputStream(
					socket.getOutputStream()));
			inputStream = new Scanner(new InputStreamReader(
					socket.getInputStream()));
			userinput = new Scanner(System.in);

		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		**/
		
	}
	
    /**
     * Returns the Last-Modified time associated with the object specified by the parameter url.
	 *
     * @param url 	URL of the object 
	 * @return the Last-Modified time in millisecond as in Date.getTime()
     */
	public long getLastModified(String url) {
		long millis = 0;
		
		return millis;
	}

}
