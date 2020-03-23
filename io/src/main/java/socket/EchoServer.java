package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final String byeMsg = "/?msg=bye";

    public static void main(String[] args) throws IOException {

        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket client = server.accept();
                try (OutputStream out = client.getOutputStream();
                     BufferedReader in = new BufferedReader(
                             new InputStreamReader(client.getInputStream()))) {
                    String str;
                    while (!(str = in.readLine()).isEmpty()) {
                        System.out.println(str);
                        if (str.toLowerCase().contains(byeMsg)) {
                            server.close();
                        }
                    }
                    out.write("HTTP/1.1 200 OK\r\n\\".getBytes());
                }
            }
        }
    }
}



