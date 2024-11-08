# Login service Assignment Video
https://github.com/user-attachments/assets/070562f1-0723-4c17-ab48-96f4c4c3f624



# LoginService with Two-Factor Authentication (2FA)

This Java application provides a simple login service with Two-Factor Authentication (2FA) using email-based One-Time Passwords (OTPs). 
Users can sign up with an email and password, then log in securely by verifying a code sent to their email.

## Overview

The program has several main components:
- **Main** - Responsible for starting the application and managing user interactions.
- **LoginService** - Manages the login workflow, including sign-up and login processes.
- **UserAccount** - Represents a user account with associated data.
- **UserAccountManager** - Handles account creation, password hashing, and validation.
- **OTPManager** - Generates and verifies OTPs sent via email.
- **OTPInfo** - Stores OTP data and expiration information.

## Getting Started

### Prerequisites
Ensure you have the following:
- Java Development Kit (JDK) installed.
- Email setup for sending OTPs (for example, using an SMTP library if implementing email sending).

### Running the Application
To run the application:
1. Compile all Java files.
2. Run the `Main` class:
   ```shell
   javac Main.java
   java Main
