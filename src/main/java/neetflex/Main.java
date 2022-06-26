package neetflex;

import neetflex.cryptography.Decryptor;
import neetflex.cryptography.Encryptor;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Main {

    public static void main(String[] args) throws NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, IOException, BadPaddingException, InvalidKeyException {
        if(args.length == 0){
            System.out.println("args: [input] [output] [encrypt/decrypt] [key(128,192,256 bit)]");
            return;
        }

        String inputFile = args[0];
        String outputPath = args[1];
        String mode = args[2].toLowerCase();
        String key = args[3];

        switch (mode){
            case "encrypt":
                new Encryptor(new File(inputFile), key).encryptFile(new File(outputPath));
                break;
            case "decrypt":
                new Decryptor(new File(inputFile), key).decryptFile(new File(outputPath));
                break;
        }
    }
}
