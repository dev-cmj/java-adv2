package io.buffered;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import static io.buffered.BufferedConst.BUFFER_SIZE;
import static io.buffered.BufferedConst.FILE_NAME;

public class ReadFileV4 {

    public static void main(String[] args) throws IOException {
        FileInputStream fis = new FileInputStream(FILE_NAME);
        long startTime = System.currentTimeMillis();

        byte[] bytes = fis.readAllBytes();
        fis.close();

        long endTime = System.currentTimeMillis();
        System.out.println("File created: " + FILE_NAME);
        System.out.println("File size: " + bytes.length / 1024 / 1024 + "MB");
        System.out.println("Time: " + (endTime - startTime) + "ms");
    }
}

class Main {
    public static void check(int[] x, int[] y) {
        if(Arrays.equals(x, y)) System.out.print("Y");
        else System.out.print("N");
    }
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4};
        int b[] = new int[] {1, 2, 3, 4};
        int c[] = new int[] {1, 2, 3};
        check(a, b);
        check(b, c);
        check(a, c);

        // Output: NNN
        // 이유는 배열은 객체이기 때문에 참조변수에는 주소값이 저장되어 있고,
        // 배열의 내용이 같더라도 주소값이 다르기 때문에 서로 다른 객체로 인식한다.
        // 따라서 배열의 길이가 같더라도 주소값이 다르기 때문에 NNN이 출력된다.
        // 배열의 내용을 비교하려면 Arrays.equals()를 사용해야 한다.

    }
}
