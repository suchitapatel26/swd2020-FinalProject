import javax.swing.*;

/**
 * The class to run server and allow connection from clients
 * @see ServerTest
 */
public class ServerTest {
    public static void main(String[] args) {
        Server application = new Server();
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.execute();
    }
}
