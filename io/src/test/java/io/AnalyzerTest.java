package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AnalyzerTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void whenReadLogFromFileThenResultInTwoLines() {
        String logFile = "./data/server.log";
        String targetFile = "./data/target";
        Analyzer an = new Analyzer();
        an.unavailable(logFile,targetFile);
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader("./data/target"))) {
            reader.lines().forEach(sj::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = new StringBuilder()
                .append("10:58:01; 10:59:01")
                .append(System.lineSeparator())
                .append("11:01:02; 11:02:02")
                .toString();
        assertThat(sj.toString(), is(result));
    }

    @Test
    public void testConnection() {
        String logFile = "";
        String targetFile = "";
        Analyzer an = new Analyzer();
        thrown.expect(IllegalStateException.class);
        an.unavailable(logFile, targetFile);
    }


}
