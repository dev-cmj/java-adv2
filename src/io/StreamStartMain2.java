package io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamStartMain2 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("temp/hello.dat");
        fileOutputStream.write(65); // A
        fileOutputStream.write(66); // B
        fileOutputStream.write(67); // C
        fileOutputStream.close();

        FileInputStream fis = new FileInputStream("temp/hello.dat");

        int readByte;
        while ((readByte = fis.read()) != -1) {
            System.out.println("readByte : " + readByte);
        }

        fis.close();
    }
}
