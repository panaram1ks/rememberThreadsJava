package FTPsimpleFileTransfer.client;

import org.apache.commons.validator.routines.InetAddressValidator;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader reader = new BufferedReader(in);

            String ipAddress = "";
            String fileName = "";

            boolean isValid = false;

            while (!isValid) {
                System.out.println("Please enter a valid Server IP address:");
                //read the entered IP address
                ipAddress = reader.readLine();
                InetAddressValidator validator = new InetAddressValidator();
                isValid = validator.isValid(ipAddress);
            }
            System.out.println("Please enter a file name: ");
            fileName = reader.readLine();

            Socket socket = new Socket(ipAddress, 9090);
            InputStream inputByte = socket.getInputStream();
            BufferedInputStream input = new BufferedInputStream(inputByte);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //send desired filename
            out.println(fileName);

            int code = input.read();
            if (code == 1) {
                BufferedOutputStream outputFile = new BufferedOutputStream(new FileOutputStream("copyFile.txt"));
                byte[] buffer = new byte[1024];
                int byteRead = 0;
                while ((byteRead = input.read(buffer)) != -1) {
                    System.out.println(".");// acts as a download indicator
                    outputFile.write(buffer, 0, byteRead);
                    outputFile.flush();
                }
                System.out.println();
                System.out.println("File: " + fileName + " was success downloaded!");
            } else {
                System.out.println("File is not present on the server");
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
