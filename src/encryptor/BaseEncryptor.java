package encryptor;

import cipher.CipherMethod;

public abstract class BaseEncryptor<T> {
    protected CipherMethod<T> cipherMethod;

    public String encrypt(String message, T key) {
        return cipherMethod.doEncryption(message, key);
    }
}
