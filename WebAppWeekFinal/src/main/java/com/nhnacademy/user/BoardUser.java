package com.nhnacademy.user;

public class BoardUser implements User{
    private String id;
    private String password;
    private String name;
    private String profileFileName;

    public BoardUser(String id, String password, String name, String profileFileName) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.profileFileName = profileFileName;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id=id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password=password;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getProfileFileName() {
        return profileFileName;
    }

    @Override
    public void setProfileFileName(String profileFileName) {
        this.profileFileName = profileFileName;
    }
}
