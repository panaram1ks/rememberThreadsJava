package Streams;

import java.io.*;

public class CharacterStream {
    public static void main(String[] args) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("example4.txt") /*, "ASCII"*/);
            InputStreamReader in = new InputStreamReader(new FileInputStream("example.txt"));

            System.out.println(out.getEncoding());

            out.write("hello character3");
//            out.close();
            out.flush();

            out.write("another string");
            out.flush();

        }catch (Exception e){
            System.out.println(e.toString());
        }
    }
}
