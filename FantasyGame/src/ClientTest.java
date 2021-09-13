import javax.swing.*;
import java.io.IOException;

/**
 * The class to run the client to connect to server
 * @see ClientTest
 */
public class ClientTest {
    public static void main(String[] args) throws IOException {
        Client application = new Client("127.0.0.1");
        application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        application.startClient();
    }
}
