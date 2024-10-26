package chat.server.command;

import chat.server.Session;
import chat.server.SessionManager;

import java.io.IOException;

public class ChangeCommand implements Command {

    private final SessionManager sessionManager;

    public ChangeCommand(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public void execute(String[] args, Session session) throws IOException {
        String changeUsername = args[1];
        sessionManager.sendAll(session.getUsername() + "님이 " + changeUsername + "으로 변경하셨습니다.");
        session.setUsername(changeUsername);
    }
}
