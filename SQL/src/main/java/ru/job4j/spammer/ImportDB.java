package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ImportDB {
    private Properties cfg;
    private String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() throws IOException {
        List<User> users;
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            users = rd.lines()
                    .map(line -> new User(line.split(";")[0], line.split(";")[1]))
                    .collect(Collectors.toList());
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("jdbc.url"),
                cfg.getProperty("jdbc.username"),
                cfg.getProperty("jdbc.password")
        )) {
            String buildTableRequest = new StringBuilder().append("CREATE TABLE IF NOT EXISTS spammer(")
                    .append("id SERIAL PRIMARY KEY,")
                    .append("name VARCHAR(100),")
                    .append("email VARCHAR(100));")
                    .toString();
            PreparedStatement pst = cnt.prepareStatement(buildTableRequest);
            pst.execute();
            pst = cnt.prepareStatement("INSERT INTO spammer (name, email) VALUES (?, ?);");
            for (User user : users) {
                pst.setString(1, user.name);
                pst.setString(2, user.email);
                pst.execute();
            }
            pst.close();
        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }


    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (FileInputStream in = new FileInputStream("/app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
