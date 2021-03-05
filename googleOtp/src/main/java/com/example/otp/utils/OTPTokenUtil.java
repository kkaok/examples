package com.example.otp.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OTPTokenUtil {
    
    private static String encodedSecurityKey;

    @Value("${system.manager.encoded-key}")
    public void setEncodedSecurityKey(String encodedSecurityKey) {
        OTPTokenUtil.encodedSecurityKey = encodedSecurityKey;
    }

    public OTPTokenUtil() {
        //throw new RuntimeException("OTPTokenUtil");
    }

    public static boolean isValidation(String code) {
        long l = new Date().getTime() / (1000 * 30);
        
        for (int i = -3; i <= 3; i++) {
            String hash = "";
            try {
                hash = getHash(l + i); //i: revision
            } catch (Exception ignore) { }
            if (code.equals(hash)) {
                return true;
            }
        }
        return false;
    }
    
    private static String getHash(long l) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] data = new byte[8];
        for (int i = 7; i >= 0; i--) {
            data[i] = (byte)l;
            l >>>= 8;
        }
        Base32 codec = new Base32();
        byte[] sKey = codec.decode(OTPTokenUtil.encodedSecurityKey);
        
        SecretKeySpec secretKeySpec = new SecretKeySpec(sKey, "HmacSHA1");
        Mac mac = Mac.getInstance("HmacSHA1");
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(data);
        int offset = hash[20 - 1] & 0xF;
        
        long truncatedHash = 0;
        for (int i = 0; i < 4; ++i) {
            truncatedHash <<= 8;
            truncatedHash |= (hash[offset + i] & 0xFF);
        }
        
        truncatedHash &= 0x7FFFFFFF;
            truncatedHash %= 1000000;
        
        return Long.toString(truncatedHash);
    }
}
