package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {

    private static final Charset EUC_KR = Charset.forName("EUC-KR");
    private static final Charset MS949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("== ASCII 영문 인코딩 ==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1); // ASCII 확장
        test("A", US_ASCII, EUC_KR); // ASCII 확장
        test("A", US_ASCII, MS949); // ASCII 확장
        test("A", US_ASCII, UTF_8); // ASCII 확장
        test("A", US_ASCII, UTF_16BE); // UTF_16 디코딩 실패

        System.out.println("== 한글 인코딩 ==");
        test("가", US_ASCII, US_ASCII); // ASCII 인코딩 실패 63(?)으로 변환
        test("가", ISO_8859_1, ISO_8859_1); // ISO_8859_1 인코딩 실패 63(?)으로 변환
        test("가", EUC_KR, EUC_KR);
        test("가", MS949, MS949);
        test("가", UTF_8, UTF_8);
        test("가", UTF_16BE, UTF_16BE);

        System.out.println("== 한글 인코딩 복잡한 문자 ==");
        test("뷁", EUC_KR, EUC_KR); // 실패
        test("뷁", MS949, MS949); // 성공
        test("뷁", UTF_8, UTF_8); // 성공
        test("뷁", UTF_16BE, UTF_16BE); // 성공

        System.out.println("== 한글 인코딩 - 디코딩이 다른 경우 ==");
        test("가", EUC_KR, MS949); // 성공
        test("뷁", MS949, EUC_KR); // 인코딩은 성공, 디코딩은 실패
        test("가", EUC_KR, UTF_8); // 실패
        test("가", MS949, UTF_8); // 실패
        test("가", UTF_8, MS949); // 실패

        System.out.println("== 영문 인코딩 - 디코딩이 다른 경우 ==");
        test("A", EUC_KR, UTF_8);
        test("A", MS949, UTF_8);
        test("A", UTF_8, MS949);
        test("A", UTF_8, UTF_16BE);
    }

    /* 한글이 깨지는 가장 큰 2가지 이유 */
    // EUC-KR(MS-949), UTF-8이 서로 호환되지 않음
    // UTF-8로 인코딩한 한글을 EUC-KR(MS-949)로 디코딩하거나 또는 그 반대로 변환하면 한글이 깨짐

    // EUC-KR(MS949) 또는 UTF-8로 인코딩한 한글을 ISO-8859-1로 디코딩하면 한글이 깨짐
    // ISO-8859-1은 1byte 문자셋이므로 한글을 표현할 수 없음

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s -> [%s] 인코딩 -> %s %sbyte -> [%s] 디코딩 -> %s\n", text, encodingCharset, Arrays.toString(encoded), encoded.length, decodingCharset, decoded);
    }
}
