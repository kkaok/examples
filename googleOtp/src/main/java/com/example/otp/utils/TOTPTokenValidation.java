package com.example.otp.utils;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;

import de.taimos.totp.TOTP;

public class TOTPTokenValidation {
    
    private static String secretKey = "INFDZDIW74N4RF3P4XWCWNZRJWIBLCTO";
    //https://www.google.com/chart?chs=200x200&chld=M|0&cht=qr&chl=otpauth://totp/TOTPTest%3Atest%40test.com?secret=INFDZDIW74N4RF3P4XWCWNZRJWIBLCTO&issuer=TOTPTest
    private TOTPTokenValidation() {
        //throw new RuntimeException("TOTPTokenUtil");
    }

    public static String getTOTPCode(String secretKey) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretKey);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public static boolean validate(String inputCode) {
        return TOTP.validate(TOTPTokenValidation.secretKey, inputCode);
    }

}
