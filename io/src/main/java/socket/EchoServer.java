package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String BYE_MSG = "/?msg=bye";
    private static boolean closeServer = false;

    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (!closeServer) {
                Socket client = server.accept();
                try (OutputStream out = client.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(client.getInputStream()))) {
                    String str;
                    str = in.readLine();
                    while (!str.isEmpty()) {
                        System.out.println(str);
                        if (str.toLowerCase().contains(BYE_MSG)) {
                            closeServer = true;
                        }
                        str = in.readLine();
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}



