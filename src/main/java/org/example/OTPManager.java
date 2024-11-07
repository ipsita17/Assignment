package org.example;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;

public class OTPManager {
    private static final int OTP_LENGTH = 6;
    private static final int OTP_VALIDITY_MINUTES = 5;
    private static final ConcurrentHashMap<String, OTPInfo> otpStorage = new ConcurrentHashMap<>();

    public boolean sendOTPAndStoreInMemory(String email) {
        UserAccount account = UserAccountManager.userAccounts.get(email);
        if (account == null || !account.is2FAEnabled) {
            return false;
        }

        String otp = generateOTP();
        LocalDateTime expiryTime = LocalDateTime.now().plusMinutes(OTP_VALIDITY_MINUTES);
        otpStorage.put(email, new OTPInfo(otp, expiryTime));

        sendEmailOTP(email, otp);
        return true;
    }

    public boolean verifyOTP(String email, String otp) {
        OTPInfo otpInfo = otpStorage.get(email);
        if (otpInfo == null) {
            System.out.println("No OTP found or OTP expired!");
            return false;
        }

        if (LocalDateTime.now().isAfter(otpInfo.expiryTime)) {
            otpStorage.remove(email);
            System.out.println("OTP has expired!");
            return false;
        }

        boolean isValid = otpInfo.otp.equals(otp);
        if (isValid) {
            otpStorage.remove(email);
        } else {
            System.out.println("Invalid OTP!");
        }

        return isValid;
    }

    private String generateOTP() {
        SecureRandom random = new SecureRandom();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }
        return otp.toString();
    }

    private void sendEmailOTP(String email, String otp) {
        System.out.println("\nSimulating email sent to " + email + " with OTP: " + otp);
        // Real implementation would involve an email API
    }
}

