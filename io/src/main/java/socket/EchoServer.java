package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String exitMsg = "exit";
    boolean shutdown = false;

    public void start() {
        ServerSocket server = null;
        try {
            server = new ServerSocket(9000);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        while (!shutdown) {
            Socket client;
            if (server != null) {
                try {
                    client = server.accept();
                    OutputStream out = client.getOutputStream();
                    InputStream in = client.getInputStream();
                    Request request = new Request(in);
                    String reqMsg = request.parse();
                    Response response = new Response(out);
                    response.setRequestMessage(reqMsg);
                    response.sendMessage();
                    shutdown = request.getUri().equalsIgnoreCase(exitMsg);
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        EchoServer server = new EchoServer();
        server.start();
    }
}



