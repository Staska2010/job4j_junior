package ru.job4j.test;

import java.util.*;

public class User {
    private String name;
    private Set<String> mails;

    User(String name, Set<String> mails) {
        this.name = name;
        this.mails = mails;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getMails() {
        return mails;
    }

    public void setMails(Set<String> mails) {
        this.mails = mails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return name.equals(user.name)
                && mails.equals(user.mails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mails);
    }
}
