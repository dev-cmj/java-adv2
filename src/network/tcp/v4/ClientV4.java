package network.tcp.v4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import static network.tcp.SocketCloseUtil.closeAll;
import static util.MyLogger.log;

public class ClientV4 {
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        log("클라이언트 시작");

        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {

            socket = new Socket("localhost", PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            log("소켓 연결: " + socket);

            Scanner scanner = new Scanner(System.in);
            while (true) {
                //서버에게 문자 보내기
                System.out.print("서버에게 보낼 문자: ");
                String toSend = scanner.nextLine();

                out.writeUTF(toSend);
                log("client -> server: " + toSend);


                if (toSend.equals("exit")) {
                    break;
                }

                //서버로부터 문자 받기
                String received = in.readUTF();
                log("server -> client: " + received);
            }

        } catch (IOException e) {
            log(e);
        } finally {
            closeAll(socket, in, out);
            log("연결 종료 : " + socket);
        }

    }
}
