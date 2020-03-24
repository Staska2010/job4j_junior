package socket;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Response {
    private static final String helloMsg = "hello";
    private static final String whatMsg = "what";
    private static final String exitMsg = "exit";
    private OutputStream out;
    private String regMsg;

    Response(OutputStream out) {
        this.out = out;
    }

    public void setRequestMessage(String regMsg) {
        this.regMsg = regMsg;
    }

    public void sendMessage() {
        switch (regMsg.toLowerCase()) {
            case helloMsg: {
                sendToOut("Hi!");
                break;
            }
            case whatMsg: {
                sendToOut("What!");
                break;
            }
            case exitMsg: {
                sendToOut("Bye!");
                break;
            }
            default: {
                sendToOut("Nicht!");
                break;
            }
        }
    }

    private void sendToOut(String message) {
        PrintWriter output = new PrintWriter(out);
        output.println("HTTP/1.1 200 OK\r\n");
        output.println(message);
        output.flush();
        output.close();
    }
}
