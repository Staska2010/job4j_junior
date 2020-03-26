package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String EXIT_MSG = "exit";
    boolean shutdown = false;

    public void start() {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!shutdown) {
                Socket client = server.accept();
                try (OutputStream out = client.getOutputStream();
                     InputStream in = client.getInputStream()) {
                    Request request = new Request(in);
                    String reqMsg = request.parse();
                    Response response = new Response(out);
                    response.init();
                    response.setRequestMessage(reqMsg);
                    response.sendMessage();
                    shutdown = request.getUri().equalsIgnoreCase(EXIT_MSG);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.start();
    }
}



