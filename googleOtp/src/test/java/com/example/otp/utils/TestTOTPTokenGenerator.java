package com.example.otp.utils;

import java.util.Scanner;

public class TestTOTPTokenGenerator {
    
	public static void main(String[] args) {
    	// generateSecurityKey();
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        if (TOTPTokenValidation.validate(code)) {
            System.out.println("Logged in successfully");
        } else {
            System.out.println("Invalid 2FA Code");
        }
    }
    
    private static void generateSecurityKey() {
        String secretKey = TOTPTokenGenerator.generateSecretKey();
        System.out.println(secretKey);
        String email = "test@test.com";
        String company = "TOTPTest";
        String barcodeUrl = TOTPTokenGenerator.getGoogleAuthenticatorBarCode(secretKey, email, company); 
        System.out.println(barcodeUrl);
    }
}
