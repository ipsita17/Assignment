package org.example;

import java.time.LocalDateTime;

public class OTPInfo {
    String otp;
    LocalDateTime expiryTime;

    OTPInfo(String otp, LocalDateTime expiryTime) {
        this.otp = otp;
        this.expiryTime = expiryTime;
    }
}
