package socket;

import java.io.*;

public class Request {
    private String uri;
    private InputStream input;

    Request(InputStream input) {
        this.input = input;
    }

    public String parse() {
        StringBuilder request = new StringBuilder();
        String buffer;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            buffer = reader.readLine();
            while (!buffer.isEmpty()) {
                request.append(buffer);
                buffer = reader.readLine();
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return parseURI(request.toString());
    }

    private String parseURI(String request) {
        uri = "";
        if (request.startsWith("GET")) {
            int position1 = request.indexOf(" ");
            int position2 = request.indexOf(" ", position1 + 1);
            uri = request.substring(position1, position2);
        }
        String prefix = "/?msg";
        if (uri.contains(prefix)) {
            uri = uri.substring(7);
        }
        return uri;
    }

    public String getUri() {
        return uri;
    }

}
