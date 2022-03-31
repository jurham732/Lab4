

	package serverSentence;

	import java.io.DataInputStream;
	import java.io.DataOutputStream;
	import java.io.IOException;
	import java.io.InputStream;
	import java.net.ServerSocket;
	import java.net.Socket;

	public class ServerSentenceApplication {

	public static void main(String[] args) throws IOException {
			
			// Launch the server frame
			ServerSentenceFrame serverFrame = new ServerSentenceFrame();
			serverFrame.setVisible(true);
			
			// Binding to a port or any other port no you are fancy of
			int portNo = 4228;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			SentenceProcessor sentenceProcessor = new SentenceProcessor();
			
			// Counter to keep track the number of requested connection
			int totalRequest = 0;
			
			// Server needs to be alive forever
			while (true) {
				
				// Message to indicate server is alive
				serverFrame.updateServerStatus(false);
				
				
				// Accept client request for connection
				Socket clientSocket = serverSocket.accept();
				
				// Read data from client
				InputStream is = clientSocket.getInputStream();
				DataInputStream dis = new DataInputStream(is);
				String sentence = dis.readUTF();
						
				
				// Process sentence
				int totalWords = sentenceProcessor.countWords(sentence);
				
				// Create stream to write data on the network
				DataOutputStream outputStream = 
						new DataOutputStream(clientSocket.getOutputStream());
				
				// Send current date back to the client
				outputStream.writeInt(totalWords);
				
				// Close the socket
				serverSocket.close();
			
				// Update the request status
				serverFrame.updateRequestStatus(
						"Data sent to the client: " + totalWords);
				serverFrame.updateRequestStatus("Accepted connection to from the "
						+ "client. Total request = " + ++totalRequest );
				
			}
			
			

		}

		
	}
	
