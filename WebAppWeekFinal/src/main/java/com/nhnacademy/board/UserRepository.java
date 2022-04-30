package com.nhnacademy.board;

import com.nhnacademy.user.User;
import java.util.List;

public interface UserRepository {
    void add(User user);
    void modify(User user);
    User remove(String id);

    User getUser(String id);
    List<User> getUsers();
}