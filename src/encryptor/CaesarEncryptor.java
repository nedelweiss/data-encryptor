package encryptor;

import cipher.CaesarCipher;

public class CaesarEncryptor extends BaseEncryptor<Integer> {
    public CaesarEncryptor() {
        cipherMethod = new CaesarCipher();
    }
}
