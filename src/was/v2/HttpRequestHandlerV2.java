package was.v2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static java.nio.charset.StandardCharsets.UTF_8;
import static util.MyLogger.log;

public class HttpRequestHandlerV2 implements Runnable {
    private final Socket socket;

    public HttpRequestHandlerV2(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            process();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void process() throws IOException {
        try (socket;
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), UTF_8));
             PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true, UTF_8)) {

            String requestString = requestToString(reader);
            if(requestString.contains("/favicon.ico")) {
                log("favicon 요청);");
                return;
            }

            log("HTTP 요청 정보 출력");
            System.out.println(requestString);

            log("HTTP 응답 생성중...");
            sleep(5000);
            responseToClient(printWriter);
            log("HTTP 응답 전달 완료");
        }
    }

    private static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    private static String requestToString(BufferedReader reader) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                break;
            }
            sb.append(line).append("\n");
        }

        return sb.toString();
    }

    private void responseToClient(PrintWriter printWriter) {
        // 웹 브라우저에 전달하는 내용
        String body = "<h1>Hello, World!</h1>";
        int length = body.getBytes(UTF_8).length;

        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\r\n")
                .append("Content-Type: text/html; charset=utf-8\r\n")
                .append("Content-Length: ").append(length).append("\r\n")
                .append("\r\n")
                .append(body);

        log("HTTP 응답 정보 출력");
        System.out.println(sb.toString());

        printWriter.println(sb.toString());
        printWriter.flush();
    }

}
