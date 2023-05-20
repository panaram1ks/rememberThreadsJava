package Streams;

import java.io.*;

public class DataStream {
    public static void main(String[] args) {

        //DataInputStream - read Java primitives
        //DataOutputStream - write Java primitives

        File file = new File("example3.txt");
        if (file.exists()) {
            System.out.println("File already exist! " + file.getName());

        } else {
            try {
                file.createNewFile();
                System.out.println("File was created! ");
                System.out.println("File path: " + file.getAbsolutePath());
            } catch (Exception e) {
                System.out.println("File can not be created! ");
                e.toString();
            }
        }

        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file.getName()));
            DataInputStream in = new DataInputStream(new FileInputStream(file.getName()));

            out.writeInt(72);
            out.writeDouble(687.01);
            out.writeFloat(123.45f);

            float var1 = in.readFloat();
            int var2 = in.readInt();
            double var3 = in.readDouble();

            System.out.println("integer value: " + var2);
            System.out.println("double value: " + var3);
            System.out.println("float value: " + var1);

            out.close();
            in.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
