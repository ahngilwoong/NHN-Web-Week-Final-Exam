package com.nhnacademy.user;

import java.util.List;

public interface Repository {
    void add(User user);

    void modify(User user);

    void remove(String id);

    User getUser(String id);

    List<User> getUsers();

    boolean isContainsChecked(String targetId);

    boolean loginValidationChecked(String id, String pwd);
}
