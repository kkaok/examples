package eblo.example.rabbitmq.hello;

import java.security.MessageDigest;
import java.util.UUID;


public class UUIDGenerator {

    private static final char[] hexadecimal = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    public static String generateUniqueKeysWithUUIDAndMessageDigest(){
        MessageDigest salt = null;
		try {
			salt = MessageDigest.getInstance("SHA-256");
	        salt.update(UUID.randomUUID()
	                .toString()
	                .getBytes("UTF-8"));
		} catch (Exception e) {
			e.printStackTrace();
		}
        return getMD5Key(bytesToHex(salt.digest()));
    }

    public static String getMD5Key(String key) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(key.getBytes("UTF-8"));
            return encode(hash);
        } catch (Exception e) {
            return key;
        }
    }

    public static String encode(byte[] binaryData) {
        if (binaryData.length != 16) {
            return null;
        }
        char[] buffer = new char[32];

        for (int i=0; i<16; i++) {
            int low = binaryData[i] & 0x0f;
            int high = (binaryData[i] & 0xf0) >> 4;
            buffer[i*2] = hexadecimal[high];
            buffer[i*2 + 1] = hexadecimal[low];
        }
        return new String(buffer);
    }
    
    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

}