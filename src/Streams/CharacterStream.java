package Streams;

import java.io.*;

public class CharacterStream {
    public static void main(String[] args) {
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream("example4.txt"));
            InputStreamReader in = new InputStreamReader(new FileInputStream("example.txt"));

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
