package neetflex.cryptography;

import neetflex.utils.EncoderUtils;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decryptor {

    private final File inputFile;
    private final byte[] secretKey;

    public Decryptor(File inputFile, String secretKey){
        this.inputFile = inputFile;
        this.secretKey = secretKey.getBytes();
    }

    public void decryptFile(File outputFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(this.secretKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] fileInputBytes = new byte[(int) inputFile.length()];
        fileInputStream.read(fileInputBytes);

        byte[] fileOutputBytes = cipher.doFinal(EncoderUtils.decodeBase64(fileInputBytes));
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        fileOutputStream.write(fileOutputBytes);

        fileInputStream.close();

        System.out.printf("Decrypted: %s%n", inputFile  .getName());
    }


}
