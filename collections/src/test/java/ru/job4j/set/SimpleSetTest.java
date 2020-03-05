package ru.job4j.set;

import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.Objects;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {
    SimpleSet<Integer> ss;

    static class User {
        int age;
        String name;
        String sex;
        User(int age, String name, String sex) {
            this.age = age;
            this.name = name;
            this.sex = sex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return age == user.age &&
                    Objects.equals(name, user.name) &&
                    Objects.equals(sex, user.sex);
        }

        @Override
        public int hashCode() {
            return Objects.hash(age, name, sex);
        }
    }

    @Before
    public void init() {
        ss = new SimpleSet<>();
    }

    @Test
    public void whenAddElementsTheyAreUnique() {
        ss.add(1);
        ss.add(2);
        ss.add(2);
        ss.add(3);
        ss.add(3);
        Iterator<Integer> ssIt = ss.iterator();
        assertThat(ssIt.next(), is(1));
        assertThat(ssIt.next(), is(2));
        assertThat(ssIt.next(), is(3));
        assertThat(ssIt.hasNext(), is(false));
    }

    @Test
    public void whenAddThreeEqualUserObjectsSetShouldStoreJustOne() {
        SimpleSet<User> userSet = new SimpleSet<>();
        User user1 = new User(10, "User1", "man");
        User user2 = new User(10, "User1", "man");
        User user3 = new User(10, "User1", "man");
        User user4 = new User(20, "User2", "man");
        userSet.add(user1);
        userSet.add(user2);
        userSet.add(user3);
        userSet.add(user4);
        Iterator<User> ssIt = userSet.iterator();
        assertThat(ssIt.next(), is(user1));
        assertThat(ssIt.next(), is(user4));
    }
}
