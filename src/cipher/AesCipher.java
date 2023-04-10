package cipher;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class AesCipher implements CipherMethod<String> {

    private static final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    private static final String HASHING_ALGORITHM = "SHA-256";
    private static final String CIPHER_ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    @Override
    public String encrypt(String message, String key) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(key));
            byte[] encryptedMessage = cipher.doFinal(message.getBytes(CHARSET_UTF_8));
            return Base64.getEncoder().encodeToString(encryptedMessage);
        } catch (GeneralSecurityException e) {
            return "Cannot get instance of transformation or hashing algorithm";
        }
    }

    // TODO: rewrite after adding decrypt method to interface
    public String decrypt(SecretKey secretKey, String encryptedMessage) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(HASHING_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        byte[] decryptedMessage = cipher.doFinal(decodedMessage);
        return new String(decryptedMessage);
    }

    private SecretKey getSecretKey(String secretKey) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        byte[] hash = messageDigest.digest(secretKey.getBytes(CHARSET_UTF_8));
        byte[] key = Arrays.copyOf(hash, 32); // TODO: extract magic number to constant
        return new SecretKeySpec(key, CIPHER_ALGORITHM);
    }
}
