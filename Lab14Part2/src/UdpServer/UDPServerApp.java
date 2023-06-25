package UdpServer;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import Controller.SentenceProcessor;

public class UDPServerApp {

	public static void main(String[] args) {
		
		System.out.println("UDPServerSideApp: Demonstration of UDP Server-Side "
				+ "Application.");
		
		
		// Permissible port for this application
		int portNo = 2259;
        
        try {
        	
    		// 1. Bind a DatagramSocket's object to a port number
            DatagramSocket datagramSocket = new DatagramSocket(portNo);
        	
            // Continually listen for request
        	while (true) {
                
                // 2. Variable to received data from the port
        		// 65535 is the maximum size for UDP packet
                byte[] receivedData = new byte[65535];
          
                // 3. Object represents packet from client
                DatagramPacket receivedPacket = 
                		new DatagramPacket(receivedData, receivedData.length);
                
                // 4. Receive packet
				datagramSocket.receive(receivedPacket);
				
				// 5. Extract data from packet
				receivedData = receivedPacket.getData();
				
				// 6. Further processing
				SentenceProcessor processor = 
						new SentenceProcessor(receivedData);
				String sentence = processor.getSentence();
				System.out.println("\nMessage received: " + sentence + ".\n");
				
				// This is not used because it give a misleading result
	            // int length = sentence.length();
	            
	            // More processing
	            int totalCharacters =  processor.countCharacters();
	            byte[] outData = processor.convertToByteArray(totalCharacters);
	            
	            // More processing to get the total vowels
	            int totalVowels =  processor.countVowels();
	            byte[] vowelsData = processor.convertToByteArray(totalVowels);
	            
	            // More processing
	            int totalConsonants =  processor.countConsonants();
	            byte[] consonantsData = processor.convertToByteArray(totalConsonants);
	            
	            // More processing
	            int totalPunctuations =  processor.countPunctuations();
	            byte[] punctuationsData = processor.convertToByteArray(totalPunctuations);
	            
	            // 7. Get the client information
	            InetAddress clientAddress =  receivedPacket.getAddress();
	            int clientPort = receivedPacket.getPort();
	            int sizeOutData = outData.length;
	            int sizevowelsData = vowelsData.length;
	            int sizeconsonantsData = consonantsData.length;
	            int sizepunctuationsData = punctuationsData.length;
	            
	            // 8. Wrap data into datagram packet
	            DatagramPacket outPacket = new DatagramPacket(outData,
	            		sizeOutData, clientAddress, clientPort);
	            
	            DatagramPacket vowelsPacket = new DatagramPacket(vowelsData,
	            		sizevowelsData, clientAddress, clientPort);
	            
	            DatagramPacket consonantsPacket = new DatagramPacket(consonantsData,
	            		sizeconsonantsData, clientAddress, clientPort);
	            
	            DatagramPacket punctuationsPacket = new DatagramPacket(punctuationsData,
	            		sizepunctuationsData, clientAddress, clientPort);
	            
	            // 9. Reply to client
	            datagramSocket.send(outPacket);
	            datagramSocket.send(vowelsPacket);
	            datagramSocket.send(consonantsPacket);
	            datagramSocket.send(punctuationsPacket);
	            
	            System.out.println("Message sent (totalCharacters) : " 
	            		+ totalCharacters  + ".\n");
	            
	            System.out.println("Message sent (totalVowels) : " 
	            		+ totalVowels  + ".\n");
	            
	            System.out.println("Message sent (totalConsonants) : " 
	            		+ totalConsonants  + ".\n");
	            
	            System.out.println("Message sent (totalPunctuations) : " 
	            		+ totalPunctuations  + ".\n");
	            
        	}
				
		} catch (IOException e) {
				
			e.printStackTrace();
        }
        
        System.out.println("UDPClientSideApp: End of program.");

	}

}
