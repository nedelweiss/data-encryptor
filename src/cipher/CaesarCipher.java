package cipher;

public class CaesarCipher implements CipherMethod<Integer> {

    private static final int ALPHABET_POWER = 26;
    private static final char STARTING_CHARACTER = 'a';

    @Override
    public String encrypt(String message, Integer key) {
        StringBuilder encrypted = new StringBuilder();
        for (char character : message.toCharArray()) {
            int originalAlphabetPosition = character - STARTING_CHARACTER;
            int newAlphabetPosition = (originalAlphabetPosition + key) % ALPHABET_POWER;
            char newCharacter = (char) (STARTING_CHARACTER + newAlphabetPosition);
            encrypted.append(newCharacter);
        }
        return encrypted.toString();
    }
}
