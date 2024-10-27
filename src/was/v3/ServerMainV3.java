package was.v3;

import was.v2.HttpServerV2;

public class ServerMainV3 {

    private static final int PORT = 12345;

    public static void main(String[] args) throws Exception {
        HttpServerV3 server = new HttpServerV3(PORT);
        server.start();
    }
}
