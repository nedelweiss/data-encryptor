package encryptor;

import cipher.AesCipher;

public class AesEncryptor extends BaseEncryptor<String> {

    public AesEncryptor() {
        super.cipherMethod = new AesCipher();
    }
}
