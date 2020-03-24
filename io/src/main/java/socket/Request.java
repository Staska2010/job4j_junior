package socket;

import java.io.*;

public class Request {
    private String URI;
    private InputStream input;

    Request(InputStream input) {
        this.input = input;
    }

    public String parse() {
        StringBuilder request = new StringBuilder();
        String buffer;
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try {
            while (!(buffer = reader.readLine()).isEmpty()) {
                request.append(buffer);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return parseURI(request.toString());
    }

    private String parseURI(String request) {
        URI = "";
        if (request.startsWith("GET")) {
            int position1 = request.indexOf(" ");
            int position2 = request.indexOf(" ", position1 + 1);
            URI = request.substring(position1, position2);
        }
        String prefix = "/?msg";
        if (URI.contains(prefix)) {
            URI = URI.substring(7);
        }
        return URI;
    }

    public String getUri() {
        return URI;
    }

}
