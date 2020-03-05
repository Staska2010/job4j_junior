package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CheckMapTest {
    @Test
    public void run() {
        User user1 = new User("firstUser");
        User user2 = new User("firstUser");
        Map<User, Object> map = new HashMap<>();
        map.put(user1, "Something");
        map.put(user2, "Something else");
        System.out.println(map);
    }
}
