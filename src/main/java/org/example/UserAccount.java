package org.example;


public class UserAccount {
    String email;
    String hashedPassword;
    String salt;
    boolean is2FAEnabled;

    UserAccount(String email, String hashedPassword, String salt, boolean is2FAEnabled) {
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.salt = salt;
        this.is2FAEnabled = is2FAEnabled;
    }
}
