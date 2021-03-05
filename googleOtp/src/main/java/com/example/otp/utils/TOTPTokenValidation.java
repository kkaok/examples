package com.example.otp.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class TOTPTokenValidation {
    
	private static String secretKey = "NS4TPIG34GXHD4VQE35HBQCQGQIKYKZS";  // 테스트용으로 만들어 놓은 거 설정 
	//private static String barcodeUrl = "https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/TOTPTest%3Atest%40test.com?secret=NS4TPIG34GXHD4VQE35HBQCQGQIKYKZS&issuer=TOTPTest";

	private TOTPTokenValidation() {
        //throw new RuntimeException("TOTPTokenValidation");
    }

    public static boolean validate(String inputCode) {
        return TOTP.validate(TOTPTokenValidation.secretKey, inputCode);
    }

}
