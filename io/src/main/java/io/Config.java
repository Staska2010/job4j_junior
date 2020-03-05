package io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(line -> linesToMap(line));
        } catch (IOException e) {
            throw new IllegalStateException();
        }
    }

    private void linesToMap(String line) {
        String[] fields;
        if (isConfigLine(line)) {
            fields = line.strip().split("=");
            values.putIfAbsent(fields[0], fields[1]);
        }
    }

    private boolean isConfigLine(String line) {
        boolean isValidFormat = false;
        if (!line.stripLeading().startsWith("#") || !line.isEmpty()) {
            if (line.strip().split("=").length == 2) {
                isValidFormat = true;
            }
        }
        return isValidFormat;
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            throw new IllegalStateException();
        }
        return out.toString();
    }
}
