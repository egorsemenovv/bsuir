package org.example;

public abstract class Person {
    private final int id;
    private String username;
    private String password;
    private String email;
    private String userStatus;

    /**
     * @param id person id
     * @param username person username
     * @param password person password
     * @param email person email
     * @param userStatus status of user (USER or ADMIN)
     */
    protected Person(int id, String username, String password, String email, String userStatus) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.userStatus = userStatus;
    }

    /**
     * @return person email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return person id
     */
    public int getId() {
        return id;
    }

    /**
     * @return person username
     */
    public String getUsername() {
        return username;
    }
}