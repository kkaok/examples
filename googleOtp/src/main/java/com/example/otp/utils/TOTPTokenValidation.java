package com.example.otp.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class TOTPTokenValidation {
    
	private static String secretKey = "4GFIVD4HAGIVOHR42TLP2ZWBKZQCPWKZ";  // 테스트용으로 만들어 놓은 거 설정 
	//private static String barcodeUrl = "https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/FTK%3AManger-Auth?secret=4GFIVD4HAGIVOHR42TLP2ZWBKZQCPWKZ&issuer=FTK";

	private TOTPTokenValidation() {
        //throw new RuntimeException("TOTPTokenValidation");
    }

    public static boolean validate(String inputCode) {
        String code = getTOTPCode();
        return code.equals(inputCode);
    }
    
    public static String getTOTPCode() {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(TOTPTokenValidation.secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }
}