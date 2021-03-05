package com.example.otp.utils;

import java.util.Scanner;

public class TestTOTPTokenGenerator {
    
	// security key 생성 후 설정
	// google otp 실행 후 아래 바코드로 otp 생성 후 테스트 
	private static String secretKey = "NS4TPIG34GXHD4VQE35HBQCQGQIKYKZS";
	private static String barcodeUrl = "https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/TOTPTest%3Atest%40test.com?secret=NS4TPIG34GXHD4VQE35HBQCQGQIKYKZS&issuer=TOTPTest";

	public static void main(String[] args) {
    	// generateSecurityKey();
        Scanner scanner = new Scanner(System.in);
        String code = scanner.nextLine();
        if (code.equals(TOTPTokenValidation.getTOTPCode(secretKey))) {
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
