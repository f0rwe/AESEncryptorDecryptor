package neetflex.utils;

import java.util.Base64;

public class EncoderUtils {
    public static String encodeBase64(byte[] bytes){
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeBase64(byte[] bytes){
        return Base64.getDecoder().decode(bytes);
    }
}
