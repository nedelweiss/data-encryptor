package encryptor;

import cipher.CaesarCipher;

public class CaesarEncryptor extends BaseEncryptor<Integer> {

    public CaesarEncryptor() {
        super.cipherMethod = new CaesarCipher();
    }
}
