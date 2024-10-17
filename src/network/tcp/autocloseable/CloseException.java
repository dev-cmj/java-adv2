package network.tcp.autocloseable;

public class CloseException extends RuntimeException {
    public CloseException(String message) {
        super(message);
    }
}
