package SMTP;

import java.io.*;
import java.net.*;
import java.util.stream.Stream;

public class EmailSocket {

    private static Socket smtpSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    //Initialization section
    //Try to open a socket on port 25
    //Try to open input and output streams
    public static void main(String[] args) {
        try {
            smtpSocket = new Socket("localhost", 25);
            in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
            out = new PrintWriter(smtpSocket.getOutputStream(), true);
        } catch (UnknownHostException e) {
            System.out.println("Don't know about host: " + smtpSocket.getInetAddress().getHostName());
        } catch (IOException e) {
            System.out.println("Couldn't get I/O for the connection to: " + smtpSocket.getInetAddress().getHostName());
        }

        //If everything has been initialized then we want to write some data
        //to the socket we have opened a connection to on port 25
        String responseLine;
        if (smtpSocket != null && out != null && in != null) {
            try {
                //STEP 1 Get a greeting by the sever
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("220") != -1){
                        break;
                    }
                }
                //STEP 2 The client initiates it's dialog by responding with a HELO command identifying itself
                out.println("HELLO " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("HELLO " + InetAddress.getLocalHost().getHostAddress());
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("250") != -1){
                        break;
                    }
                }
                //STEP 3 The client notifies the receiver of the originating email address of the message in a MAIL FROM command
                out.println("MAIL From: mytest@test.com");
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("250") != -1){
                        break;
                    }
                }
                //STEP 4 The client notifies the receiver ot the recipient email address of the message in a RCPT TO command
                out.println("RCPT TO: parom.evgen@mail.ru");
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("250") != -1){
                        break;
                    }
                }
                //STEP 5 Send DATA command
                out.println("DATA");
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("354") != -1){
                        break;
                    }
                }
                //STEP 6 Send Email body
                out.println("From: mytest@test.com");
                out.println("TO: parom.evgen@mail.ru");
                out.println("Subject: TEST EMAIL");
                out.println();
                out.println("Subject: TEST BODY");
                out.println("This is a test message");
                out.println("Thanks!");
                out.println("Java Networking Programming course");
                out.println();
                out.println(".");
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("250") != -1){
                        break;
                    }
                }
                //STEP 7 Send Quit command
                out.println("QUIT");
                while ((responseLine = in.readLine()) != null){
                    System.out.println("Server: " + responseLine);
                    if(responseLine.indexOf("221") != -1){
                        break;
                    }
                }
                System.out.println("Email successfully sent!");

                //close all resources
                out.close();
                in.close();
                smtpSocket.close();
            } catch (Exception e) {

            }
        }
    }
}
