package io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;


public class ConfigTest {
    @Rule
    public ExpectedException prospException = ExpectedException.none();

    @Test
    public void whenCheckRightConfigFile() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.dialect"),
                is("org.hibernate.dialect.PostgreSQLDialect")
        );
    }

    @Test
    public void whenCheckFileWithCommentsAndWrongValues() {
        String path = "./data/file.comments";
        Config config = new Config(path);
        config.load();
        assertNull(
                config.value("hibernate.connection.url")
        );
        assertNull(
                config.value("hibernate.dialect")
        );
    }

    @Test
    public void whenCheckNonExistingFile() {
        String path = "some_file";
        Config config = new Config(path);
        prospException.expect(IllegalStateException.class);
        config.load();
    }

    @Test
    public void testToStringFunction() {
        StringJoiner sj = new StringJoiner(System.lineSeparator());
        sj.add("## PostgreSQL")
                .add("")
                .add("hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect")
                .add("hibernate.connection.url=jdbc:postgresql://127.0.0.1:5432/trackstudio")
                .add("hibernate.connection.driver_class=org.postgresql.Driver")
                .add("hibernate.connection.username=postgres")
                .add("hibernate.connection.password=password");
        Config config = new Config("./data/app.properties");
        assertThat(config.toString(), is(sj.toString()));
    }
}
