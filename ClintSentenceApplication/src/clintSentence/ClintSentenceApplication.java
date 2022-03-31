package clintSentence;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClintSentenceApplication {

	public static void main(String[] args) 
			throws UnknownHostException, IOException {
		
		// Launch client-side frame
		ClintSentenceFrame clientSentenceFrame = new ClintSentenceFrame();
		clientSentenceFrame.setVisible(true);
		
		// Connect to the server @ localhost, port 4228
		Socket socket = new Socket(InetAddress.getLocalHost(), 4228);
		
		// Update the status of the connection
		clientSentenceFrame.updateConnectionStatus(socket.isConnected());
		
		String sentence = "Create a TCP-Based client-server application " + "to process a length of text";
		
		
		//Send Data to Server
		
		OutputStream os = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(os);
		dos.writeUTF(sentence);
		
		//Get response from the server
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		int totalWords = dis.readInt();
		
		clientSentenceFrame.updateLengthLable(totalWords);
		
		System.out.println("Total words : " + totalWords);
	
		
		// Close everything
		socket.close();

	}
	
}
