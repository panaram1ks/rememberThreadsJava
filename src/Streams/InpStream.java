package Streams;

import java.io.*;
public class InpStream {

    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream("example.txt");
            int data = fileInputStream.read();

            while(data != -1){
                System.out.println(data);
                System.out.println((char) data);
                data= fileInputStream.read();
            }
            fileInputStream.close();

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

}
