package com.nhnacademy.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepository implements Repository{
    private static UserRepository userRepository;
    private final List<User> users = new ArrayList<>();

    private UserRepository(){
    }

    public static UserRepository getInstance(){
        if(Objects.isNull(userRepository))
            userRepository = new UserRepository();
        return userRepository; //반환.
    }
    @Override
    public void add(User user){
        users.add(user);
    }

    @Override
    public void modify(User user) {

    }
    @Override
    public List<User> getUsers(){
        return users;
    }
    @Override
    public void remove(String targetId){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(targetId)){
                users.remove(i);
                break;
            }
        }
    }
    @Override
    public User getUser(String targetId){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(targetId)){
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean isContainsChecked(String targetId){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(targetId)){
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean loginValidationChecked(String id, String pwd){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id) && users.get(i).getPassword().equals(pwd)){
                return true;
            }
        }
        return false;
    }



}
