package ru.job4j.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MailAnalyzerTest {

    @Test
    public void whenAddTwoIntersectingUsersThenOuputIsOneUser() {
        User user1 = new User("user1", Set.of("1@mail.ru", "2@mail.ru"));
        User user2 = new User("user2", Set.of("3@mail.ru", "2@mail.ru"));
        MailAnalyzer ma = new MailAnalyzer();
        List<User> result = ma.analyze(List.of(user1, user2));
        List<User> expected = new ArrayList<>();
        expected.add(new User("user2", Set.of("1@mail.ru", "2@mail.ru", "3@mail.ru")));
        assertThat(result, is(expected));
    }

    @Test
    public void whenAddTwoNonIntersectingUsersThenOutputIsTwoUsers() {
        User user1 = new User("user1", Set.of("1@mail.ru", "2@mail.ru"));
        User user2 = new User("user2", Set.of("4@mail.ru", "3@mail.ru"));
        MailAnalyzer ma = new MailAnalyzer();
        List<User> result = ma.analyze(List.of(user1, user2));
        List<User> expected = new ArrayList<>();
        expected.add(user2);
        expected.add(user1);
        assertThat(result, is(expected));
    }

    @Test
    public void whenAnalyzeTaskInputThenGetCorrectResult() {
        User user1 = new User("user1", Set.of("xxx@ya.ru", "foo@gmail.com", "lol@mail.ru"));
        User user2 = new User("user2", Set.of("foo@gmail.com", "ups@pisem.net"));
        User user3 = new User("user3", Set.of("xyz@pisem.net", "vasya@pupkin.com"));
        User user4 = new User("user4", Set.of("ups@pisem.net", "aaa@bbb.ru"));
        User user5 = new User("user5", Set.of("xyz@pisem.net"));
        MailAnalyzer ma = new MailAnalyzer();
        List<User> result = ma.analyze(List.of(user1, user2, user3, user4, user5));
        List<User> expected = new ArrayList<>();
        User expectedUser1 = new User("user4", Set.of("aaa@bbb.ru", "foo@gmail.com", "lol@mail.ru", "ups@pisem.net", "xxx@ya.ru"));
        User expectedUser2 = new User("user5", Set.of("vasya@pupkin.com", "xyz@pisem.net"));
        expected.add(expectedUser1);
        expected.add(expectedUser2);
        assertThat(result, is(expected));
    }
}
