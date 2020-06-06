package encryptor;

import cipher.AesCipher;

public class AesEncryptor extends BaseEncryptor<String> {
    public AesEncryptor() {
        cipherMethod = new AesCipher();
    }
}
