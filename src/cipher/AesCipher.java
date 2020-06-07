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
    private final Charset CHARSET_UTF_8 = StandardCharsets.UTF_8;
    private final String HASHING_ALGORITHM = "SHA-256";
    private final String CIPHER_ALGORITHM = "AES";
    private final String TRANSFORMATION = "AES/ECB/PKCS5Padding";

    @Override
    public String doEncryption(String message, String key) {
        try {
            final SecretKey secretKey = getSecretKey(key);
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            final byte[] messageInBytes = message.getBytes(CHARSET_UTF_8);
            final byte[] encryptedMessage = cipher.doFinal(messageInBytes);

            return Base64.getEncoder().encodeToString(encryptedMessage);
        } catch (GeneralSecurityException e) {
            return "Cannot get instance of transformation or hashing algorithm";
        }
    }

    public String decrypt(SecretKey secretKey, String encryptedMessage) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        final Cipher cipher = Cipher.getInstance(HASHING_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        final byte[] decodedMessage = Base64.getDecoder().decode(encryptedMessage);
        final byte[] decryptedMessage = cipher.doFinal(decodedMessage);
        return new String(decryptedMessage);
    }

    private SecretKey getSecretKey(String secretKey) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance(HASHING_ALGORITHM);
        final byte[] secretKeyInBytes = secretKey.getBytes(CHARSET_UTF_8);
        final byte[] hash = messageDigest.digest(secretKeyInBytes);
        final byte[] key = Arrays.copyOf(hash, 32);
        return new SecretKeySpec(key, CIPHER_ALGORITHM);
    }
}
