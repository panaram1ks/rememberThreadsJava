package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {

    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            boolean stop = false;
            while (!stop){
                System.out.println("Waiting for clients...");
                Socket socket = serverSocket.accept();
                System.out.println("Client is connected.");

                ClientThread clientThread = new ClientThread(socket);
                clientThread.start();
            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }


    }


}
