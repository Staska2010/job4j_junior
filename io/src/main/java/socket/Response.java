package socket;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Response {
    private OutputStream out;
    private String regMsg;
    Map<String, String> responses = new HashMap<>();

    Response(OutputStream out) {
        this.out = out;
    }

    public void setRequestMessage(String regMsg) {
        this.regMsg = regMsg;
    }

    public void init() {
        responses.put("hello", "Hello, my dear friend!");
        responses.put("what", "what?");
        responses.put("exit", "See you!");
    }

    public void sendMessage() {
        String key = regMsg.toLowerCase();
        sendToOut(responses.getOrDefault(key, "Nothing to answer..."));
    }

    private void sendToOut(String message) {
        PrintWriter output = new PrintWriter(out);
        output.println("HTTP/1.1 200 OK\r\n");
        output.println(message);
        output.flush();
        output.close();
    }
}
