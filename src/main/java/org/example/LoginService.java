package org.example;



import java.util.Scanner;

public class LoginService {
    private final UserAccountManager userAccountManager = new UserAccountManager();
    private final OTPManager otpManager = new OTPManager();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n=== Login Service Demo ===");
            System.out.println("1. Sign Up and Login");
            System.out.println("2. Login (for existing users)");
            System.out.println("3. Exit");
            System.out.print("Choose an option (1-3): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("\n--- Sign Up Process ---");
                    System.out.print("Enter email: ");
                    String signUpEmail = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String signUpPassword = scanner.nextLine();

                    if (userAccountManager.signUp(signUpEmail, signUpPassword)) {
                        System.out.println("\nProceeding to login...");
                        processLogin(scanner, signUpEmail);
                    }
                    break;

                case "2":
                    System.out.println("\n--- Login Process ---");
                    System.out.print("Enter email: ");
                    String loginEmail = scanner.nextLine();
                    processLogin(scanner, loginEmail);
                    break;

                case "3":
                    System.out.println("Thank you for using our service!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
        scanner.close();
    }

    private void processLogin(Scanner scanner, String email) {
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userAccountManager.validateCredentials(email, password)) {
            System.out.println("\nCredentials validated successfully!");
            System.out.println("Proceeding with 2-Factor Authentication...");

            if (otpManager.sendOTPAndStoreInMemory(email)) {
                System.out.println("\nOTP has been sent to your registered email.");
                System.out.print("Enter the OTP received: ");
                String otp = scanner.nextLine();

                if (otpManager.verifyOTP(email, otp)) {
                    System.out.println("\n=== Login Successful! ===");
                    System.out.println("Welcome, " + email + "!");
                } else {
                    System.out.println("\nLogin failed due to invalid OTP!");
                }
            }
        }
    }
}


