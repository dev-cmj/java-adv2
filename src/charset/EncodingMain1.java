package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 처리 ==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_KR);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE); // 2byte encoding
        encoding("A", MS949);

        System.out.println("== 한글 처리 ==");
        encoding("가", EUC_KR);
        encoding("가", MS949);
        encoding("가", UTF_8); // 3byte encoding (UTF-8)
        encoding("가", UTF_16BE); // 2byte

        String str = "a";
        byte[] bytes = str.getBytes(); // 캐릭터 셋 지정하지 않으면 기본 캐릭터 셋으로 인코딩된다!
        System.out.println("bytes = " + Arrays.toString(bytes));
    }

    /*
    문자를 컴퓨터가 이해할 수 있는 바이트로 변환하는 것을 문자 인코딩이라고 합니다.
    String.getBytes(Charset charset) 메소드를 이용하면 문자열을 바이트 배열로 변환할 수 있습니다.
    문자를 byte로 변경하려면 문자 집합이 필요합니다. Charset 클래스는 문자 집합을 제공합니다.
     */

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }

}
