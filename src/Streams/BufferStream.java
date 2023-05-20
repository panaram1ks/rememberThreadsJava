package Streams;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class BufferStream {
    public static void main(String[] args) {

        try {
            BufferedReader br = new BufferedReader(new FileReader("example6.txt"));

            BufferedWriter bw = new BufferedWriter(new FileWriter("example7.txt"));
            String line = null;
            while ((line = br.readLine()) != null){
                bw.write(line);
                bw.newLine();
            }
            br.close();
            bw.close();
        }catch (Exception e){

        }

    }
}
