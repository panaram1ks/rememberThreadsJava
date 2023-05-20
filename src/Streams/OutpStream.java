package Streams;

import java.io.*;

public class OutpStream {
    public static void main(String[] args) {
        try {
            //overrides file default behaviour
//            FileOutputStream fileOutputStream = new FileOutputStream("example2.txt");

            //turn on append mod true
            FileOutputStream fileOutputStream2 = new FileOutputStream("example2.txt", true);

            String myText = "Interest thing these IO's streams";
            String myText2 = "Second text";
//            fileOutputStream.write(myText.getBytes());

            fileOutputStream2.write(myText2.getBytes());
            fileOutputStream2.write(myText.getBytes());

            fileOutputStream2.close();
//            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
