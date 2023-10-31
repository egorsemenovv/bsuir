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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public abstract void hello();

    public abstract void startService();

    @Override
    public String toString() {
        return Integer.toString(id) + " " + username + " " + email + " " + userStatus;
    }
}