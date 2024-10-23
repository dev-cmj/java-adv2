package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static util.MyLogger.log;

public class Server {
    private final int port;
    private final CommandManager commandManager;
    private final SessionManager sessionManager;

    private ServerSocket serverSocket;

    public Server(int port, CommandManager commandManager, SessionManager sessionManager) {
        this.port = port;
        this.commandManager = commandManager;
        this.sessionManager = sessionManager;
    }

    public void start() throws IOException {
        log("서버 시작: " + commandManager.getClass());
        serverSocket = new ServerSocket(port);
        log("서버 소켓 시작 - 리스닝 포트: " + port);

        addShutdownHook();
        running();
    }

    private void addShutdownHook() {
        ShutdownHook hook = new ShutdownHook(sessionManager, serverSocket);
        Runtime.getRuntime().addShutdownHook(new Thread(hook, "shutdown"));
    }

    private void running() {
        try {
            while (true) {
                Socket socket = serverSocket.accept(); // 클라이언트가 접속할 때까지 대기
                log("소켓 연결: " + socket);

                Session session = new Session(socket, commandManager, sessionManager);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            log("서버 소켓 종료: " + e);
        }
    }

    static class ShutdownHook implements Runnable {
        private final SessionManager sessionManager;
        private final ServerSocket serverSocket;

        public ShutdownHook(SessionManager sessionManager, ServerSocket serverSocket) {
            this.sessionManager = sessionManager;
            this.serverSocket = serverSocket;
        }

        @Override
        public void run() {
            log("shutdown hook 실행");

            try {
                sessionManager.closeAll();
                serverSocket.close();

                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("e = " + e);
            }
        }
    }
}
