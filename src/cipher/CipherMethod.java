package cipher;

public interface CipherMethod<T> {

    String encrypt(String message, T key);

    // TODO: create decrypt method
}
