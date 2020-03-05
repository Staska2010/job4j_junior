package ru.job4j.map;

import java.util.Calendar;

public class User {
    private String name;
    private int children;
    Calendar birthday;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return children == user.children &&
//                Objects.equals(name, user.name) &&
//                Objects.equals(birthday, user.birthday);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(name, children, birthday);
//    }
}
