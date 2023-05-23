package UDP;

import java.io.*;
import java.net.*;

/**
 * @author E.Parominsky 23/05/2023 08:25
 */
public class UDPServer {

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(9090);
            byte[] sendData = new byte[1024]; //store outgoing data
            byte[] receiveData = new byte[1024]; //store incoming data

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                socket.receive(receivePacket);

                String sentence = new String(receivePacket.getData());
                System.out.println("Received: " + sentence);

                String stringData = "Hello client";
                sendData = stringData.getBytes();

                InetAddress clientIpAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientIpAddress, clientPort);

                socket.send(sendPacket);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }
}
