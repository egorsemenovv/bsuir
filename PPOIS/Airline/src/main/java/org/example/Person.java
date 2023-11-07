package org.example;

public abstract class Person {
    private final int id;
    private String username;
    private String password;
    private String email;
    private String userStatus;

    protected Person(int id, String username, String password, String email, String userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}