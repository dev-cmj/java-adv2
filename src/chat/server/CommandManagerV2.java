package chat.server;

import java.io.IOException;
import java.util.List;

public class CommandManagerV2 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final SessionManager sessionManager;

    public CommandManagerV2(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        // /join|han
        if (totalMessage.startsWith("/join")) {
            String[] tokens = totalMessage.split(DELIMITER);
            String username = tokens[1];
            session.setUsername(username);
            sessionManager.sendAll(username + "님이 입장하셨습니다.");
        } else if (totalMessage.startsWith("/message")) {
//            "/message|{내용}"
            String[] tokens = totalMessage.split(DELIMITER);
            String message = tokens[1];
            sessionManager.sendAll("[" + session.getUsername() + "] " + message);
        } else if (totalMessage.startsWith("/change")) {
            String[] tokens = totalMessage.split(DELIMITER);
            String changeUsername = tokens[1];
            sessionManager.sendAll(session.getUsername() + "님이 " + changeUsername + "으로 변경하셨습니다.");
            session.setUsername(changeUsername);
        } else if (totalMessage.startsWith("/users")) {
            List<String> usernames = sessionManager.getAllUsername();
            StringBuilder sb = new StringBuilder();
            sb.append("전체 접속자 : ").append(usernames.size()).append("\n");
            for (String username : usernames) {
                sb.append(username).append("\n");
            }
            session.send(sb.toString());
        } else if (totalMessage.startsWith("/exit")) {
            throw new IOException("exit");
        } else {
            session.send("처리할 수 없는 명령어 입니다. : " + totalMessage);
        }
    }
}
