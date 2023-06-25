package UdpClient;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientApp {

	public static void main(String[] args) {
		
        System.out.println("\n\tUDPClientSideApp: Demonstration of UDP "
                + "Client-Side Application.");

        try {

            // 1. Define server port number and address
            int portNo = 2259;
            InetAddress ip = InetAddress.getLocalHost();

            // 2. Prepare and transform data into bytes
            String text = "Good morning Malaysia Singapore Vietnam.";
            byte buf[] = text.getBytes();

            // 3. Wrap data in datagram packet (constructor no 5)
            DatagramPacket outPacket =
                    new DatagramPacket(buf, buf.length, ip, portNo);

            // 4. Create the socket object to transmit the data.
            DatagramSocket datagramSocket = new DatagramSocket();

            // 5. Send datagram packet
            datagramSocket.send(outPacket);
            System.out.println("\n\tSending '" + text + "' (" + text.length()
                    + ") out on network.");

            // 6. New buffer to extract the received data
            byte[] inData = new byte[65535];

            // 7. Packets to receive data
            DatagramPacket inPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket vowelsPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket consonantsPacket = new DatagramPacket(inData, inData.length);
            DatagramPacket punctuationsPacket = new DatagramPacket(inData, inData.length);

            // 8. Receive data
            datagramSocket.receive(inPacket);
            datagramSocket.receive(vowelsPacket);
            datagramSocket.receive(consonantsPacket);
            datagramSocket.receive(punctuationsPacket);

            // 9. Extract data
            byte[] receivedData = inPacket.getData();
            byte[] vowelsData = vowelsPacket.getData();
            byte[] consonantsData = consonantsPacket.getData();
            byte[] punctuationsData = punctuationsPacket.getData();

            // 10. Further processing
            // Transform data into human readable text
            String length = new String(receivedData, 0, inPacket.getLength());
            String vowelsLength = new String(vowelsData, 0, vowelsPacket.getLength());
            String consonantsLength = new String(consonantsData, 0, consonantsPacket.getLength());
            String punctuationsLength = new String(punctuationsData, 0, punctuationsPacket.getLength());

            // Display the data received from the server
            System.out.println("\tLength from the server is: " + length);
            System.out.println("\tVowels Length from the server is: " + vowelsLength);
            System.out.println("\tConsonant Length from the server is: " + consonantsLength);
            System.out.println("\tPunctuation Length from the server is: " + punctuationsLength);

            datagramSocket.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		System.out.println("\n\tUDPClientSideApp: End of program.");

	}

}
