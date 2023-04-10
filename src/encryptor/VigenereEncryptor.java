package encryptor;

import cipher.VigenereCipher;

public class VigenereEncryptor extends BaseEncryptor<String> {

    public VigenereEncryptor() {
        super.cipherMethod = new VigenereCipher();
    }
}
