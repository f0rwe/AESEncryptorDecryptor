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

public class Encryptor {

    private final File inputFile;
    private final byte[] secretKey;

    public Encryptor(File inputFile, String secretKey){
        this.inputFile = inputFile;
        this.secretKey = secretKey.getBytes();
    }

    public void encryptFile(File outputFile) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IOException, IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = new SecretKeySpec(this.secretKey, "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        FileInputStream fileInputStream = new FileInputStream(inputFile);
        byte[] fileInputBytes = new byte[(int) inputFile.length()];
        fileInputStream.read(fileInputBytes);

        byte[] fileOutputBytes = cipher.doFinal(fileInputBytes);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write( EncoderUtils.encodeBase64(fileOutputBytes));
        outputStreamWriter.flush();

        fileInputStream.close();
        outputStreamWriter.close();;

        System.out.printf("Encrypted: %s%n", inputFile.getName());
    }

}
