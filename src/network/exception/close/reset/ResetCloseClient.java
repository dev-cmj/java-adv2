package network.exception.close.reset;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;

import static util.MyLogger.log;

public class ResetCloseClient {

    public static void main(String[] args) throws InterruptedException, IOException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        //client <- server :FIN
        Thread.sleep(1000); // 서버가 close() 호출할 때까지 대기

        //client -> server : PUSH[1]
        out.write(1);

        //client <- server : RST
        Thread.sleep(1000); // RST 메시지 전송 대기

        try {
            //java.net.SocketException: Connection reset
            int read = in.read();
            log("read = " + read);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            //java.net.SocketException: Broken pipe (Write failed)
            out.write(1);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
