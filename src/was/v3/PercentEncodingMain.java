package was.v3;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class PercentEncodingMain {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String encode = URLEncoder.encode("a", StandardCharsets.UTF_8);
        System.out.println("encode = " + encode);
    }
}
