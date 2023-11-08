package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Password {

    private Password(){}

    /**
     * @param password password
     * @return true if password is appropriate, false if not
     */
    public static boolean isAppropriate(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    /**
     * encode password to store in database
     * @param password password
     * @return encoded string
     */
    public static String encode (String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

}
