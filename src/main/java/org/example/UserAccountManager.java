package org.example;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.concurrent.ConcurrentHashMap;

public class UserAccountManager {
    private static final int SALT_LENGTH = 16;
    static final ConcurrentHashMap<String, UserAccount> userAccounts = new ConcurrentHashMap<>();

    public boolean signUp(String email, String password) {
        if (userAccounts.containsKey(email)) {
            System.out.println("Email already exists! Please try a different email.");
            return false;
        }

        String salt = generateSalt();
        String hashedPassword = hashPassword(password, salt);

        UserAccount newAccount = new UserAccount(email, hashedPassword, salt, true);
        userAccounts.put(email, newAccount);
        System.out.println("Sign up successful!");
        return true;
    }

    public boolean validateCredentials(String email, String password) {
        UserAccount account = userAccounts.get(email);
        if (account == null) {
            System.out.println("User not found!");
            return false;
        }

        String hashedAttempt = hashPassword(password, account.salt);
        boolean isValid = hashedAttempt.equals(account.hashedPassword);

        if (!isValid) {
            System.out.println("Invalid password!");
        }

        return isValid;
    }

    private String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String hashPassword(String password, String salt) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String saltedPassword = password + salt;
            byte[] hash = digest.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }
}

