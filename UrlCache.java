package assignment1;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * UrlCache Class
 * 
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
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
		int portNumber = 80;
		
		String line = "";
		byte[] byteArray = new byte[1024 * 10];
		
		PrintWriter outputStream;

		/* url String Parsing */
		
		hostName = url.substring(0, url.indexOf("/"));
		pathName = url.substring(url.indexOf("/"));
		
		if(url.indexOf(":") != -1) {
			hostName = url.substring(0, url.indexOf(":"));
			portNumber = Integer.parseInt(url.substring(url.indexOf(":") + 1, url.indexOf("/")));
			
		}
					
		System.out.println(hostName);
		System.out.println(pathName);
		System.out.println(portNumber + "\n");
		
		
		
		

		try {
			// connects to port server app listening at port 8888 in the same
			// machine
			Socket socket = new Socket(hostName, portNumber);

			// Create necessary streams
			outputStream = new PrintWriter(new DataOutputStream(
					socket.getOutputStream()));
			
			outputStream.print("GET " + pathName + " HTTP/1.1\r\n");
			outputStream.print("Host: "+ hostName + ":" + portNumber + "\r\n");
			//outputStream.print("If-modified-since: " + "" + "\r\n");
			outputStream.print("\r\n");
			outputStream.flush();

			
			byte[] http_response_header_bytes = new byte[2048];
			byte[] http_object_bytes = new byte[1024];
			String http_response_header_string = "";
			
			int off = 0;
			int num_byte_read = 0;
			

			/*read http header*/
			
			try {
			while(num_byte_read != -1) {
				socket.getInputStream().read(http_response_header_bytes, off, 1);				
				off++;
				http_response_header_string = new String(http_response_header_bytes, 0, off, "US-ASCII");
				if(http_response_header_string.contains("\r\n\r\n"))
						break;
				}
			}
			catch(IOException e) {
				//exception handling
			}
			
			Scanner headScanner = new Scanner(http_response_header_string);
			String lastModified = "";
			int objectLength = 0;
			
			while(headScanner.hasNextLine()) {
				line = headScanner.nextLine();
				
				if(line.contains("Last-Modified")) {
					lastModified = line.substring(line.indexOf(":") + 2);
				}else if(line.contains("Content-Length")) {
					objectLength = Integer.parseInt(line.substring(line.indexOf(":") + 2));
				}
			}
			
			headScanner.close();

			System.out.println("contentLength = " + objectLength);
			System.out.println("lastModified = " + lastModified);
			System.out.println(http_response_header_string);
			
			if(http_response_header_string.contains("304 Not Modified")) {
					//logic for not modified files;
			}
			else if(http_response_header_string.contains("200 OK")){
				
				int counter = 0; //keeps track of amount of bytes read
				
				
				File f = new File(hostName + pathName);
				f.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(f);
				
				try {
					while(num_byte_read != -1) {
						
						if(counter == objectLength) {
							
						System.out.println("counter == object length");	
							
							break;
						}	
						//read some amount of bytes and write them to file
						

						num_byte_read = socket.getInputStream().read(http_object_bytes);
						
						fos.write(http_object_bytes);
						
						counter+= num_byte_read;
						System.out.println(counter);
						
					}
					
					
					fos.close();
					
				}
				catch(IOException e) {
					//error for downloading file
				}
			}
			
			
			socket.close();
				
		}
		catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		

		System.out.println("------------------------------------------");

		
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