package org.example;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Password {

    private Password(){}

    public static boolean isAppropriate(String password){
        Pattern pattern = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!#$%&? \"]).*$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

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

    public static boolean checkPassword(String password, String encodedHash) throws NoSuchAlgorithmException {
        return encode(password).equals(encodedHash);
    }
}
