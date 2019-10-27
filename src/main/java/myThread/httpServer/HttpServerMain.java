package myThread.httpServer;

public class HttpServerMain {

    public static void main(String[] args) {
        SimpleHttpServer.setBasePath("D:/project/testDir"); //将根目录定义到html所在的目录
        try {
            SimpleHttpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
