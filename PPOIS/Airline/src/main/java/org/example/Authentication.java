package org.example;

import java.util.Scanner;

public class Authentication {
    public Person person;
    private static final DataBaseManager db = new DataBaseManager();

    /**
     * to log in into your account
     * @param username person`s username
     * @param password person`s password
     * @return true if successful, false if not
     */
    public boolean logIn(String username,String password) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
//        String username = "First User"; // scanner.nextLine();
        System.out.println("Password: ");
//        String password = scanner.nextLine();
        if (!Password.isAppropriate(password)) {
            System.out.println("Wrong password");
            return false;
        }
        person = db.getUserFromDataBase(username, Password.encode(password));
        if (person == null){
            System.out.println("No such user. Try again...");
            return false;
        }
        return true;
    }

    /**
     * creates new user
     * @param username person`s username
     * @param password person`s password
     * @param email person`s email
     * @return true if successful, false if not
     */
    public boolean signUp(String username,String password, String email) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Username: ");
//        String username = scanner.nextLine();
        System.out.println("Password: ");
//        String password = scanner.nextLine();
        if (!Password.isAppropriate(password)) {
            System.out.println("Wrong password");
            return false;
        }
        System.out.println("Email: ");
//        String email = scanner.nextLine();
        return db.addUser(username, Password.encode(password), email);
    }
}
