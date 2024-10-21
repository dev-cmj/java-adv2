package network.exception.close.normal;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class NormalCloseClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("localhost", 12345);
        log("소켓 연결: " + socket);
        InputStream in = socket.getInputStream();

        readByInputStream(in, socket);
        readByBufferedReader(in, socket);
        readByDataInputStream(in, socket);

        socket.close();
        log("소켓 종료: " + socket);
    }

    private static void readByDataInputStream(InputStream in, Socket socket) throws IOException {
        DataInputStream dis = new DataInputStream(in);

        try {
            dis.readUTF();
        } catch (IOException e) {
            log(e);
        } finally {
            dis.close();
            socket.close();
        }
    }

    private static void readByInputStream(InputStream in, Socket socket) throws IOException {
        int read = in.read();
        log("read = " + read);
        if (read == -1) {
            in.close();
            socket.close();
        }
    }

    private static void readByBufferedReader(InputStream in, Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String readString = reader.readLine();
        log("readString = " + readString);
        if (readString == null) {
            reader.close();
            socket.close();
        }

    }
}
