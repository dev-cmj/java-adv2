package network.tcp.autocloseable;

public class ResourceCloseMainV3 {

    public static void main(String[] args) {

        try {
            logic();
        } catch (CallException e) {
            System.out.println("CallException 예외처리");
            throw new RuntimeException(e);
        } catch (CloseException e) {
            System.out.println("CloseException 예외처리");
            throw new RuntimeException(e);
        }
    }

    private static void logic() throws CallException, CloseException {

        ResourceV1 resource1 = null;
        ResourceV1 resource2 = null;

        try {
            resource1 = new ResourceV1("resource1");
            resource2 = new ResourceV1("resource2");

            resource1.call();
            resource2.callEx(); // CallException 발생
        } catch (CallException e) {
            System.out.println("ex: " + e);
            throw e;
        } finally {
            try {
                if (resource2 != null) {
                    resource2.closeEx();
                }
            } catch (CloseException e) {
                // close() 에서 발생한 예외는 버린다. 필요하면 로깅 정도
                System.out.println("ex: " + e);
            }

            try {
                if (resource1 != null) {
                    resource1.closeEx();
                }
            } catch (CloseException e) {
                // close() 에서 발생한 예외는 버린다. 필요하면 로깅 정도
                System.out.println("ex: " + e);
            }
        }
    }
}
