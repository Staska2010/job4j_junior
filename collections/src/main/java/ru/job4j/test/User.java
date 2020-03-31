package ru.job4j.test;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {
    private String name;
    private List<String> mails;
    User() {
        this.name = "";
        this.mails = new LinkedList<>();
    }

    User(String name, List<String> mails) {
        this.name = name;
        this.mails = mails;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getMails() {
        return mails;
    }

    public void setMails(List<String> mails) {
        this.mails = mails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return name.equals(user.name)
                && mails.equals(user.mails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, mails);
    }
}
