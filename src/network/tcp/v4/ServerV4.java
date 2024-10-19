package network.tcp.v4;



import network.tcp.v3.SessionV3;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class ServerV4 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("서버 시작");
        ServerSocket serverSocket = new ServerSocket(PORT);
        log("서버 소켓 시작 - 리스닝 포트: " + PORT);

        while (true) {
            Socket socket = serverSocket.accept(); // 클라이언트가 접속할 때까지 대기
            log("소켓 연결: " + socket);

            SessionV4 session = new SessionV4(socket);
            Thread thread = new Thread(session);
            thread.start();
        }


    }
}
