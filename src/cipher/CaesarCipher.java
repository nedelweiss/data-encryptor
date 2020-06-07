package cipher;

public class CaesarCipher implements CipherMethod<Integer> {
    @Override
    public String doEncryption(String message, Integer key) {
        final StringBuilder encrypted = new StringBuilder();
        final char startingCharacter = 'a';
        final int alphabetPower = 26;
        for (char character : message.toCharArray()) {
            int originalAlphabetPosition = character - startingCharacter;
            int newAlphabetPosition = (originalAlphabetPosition + key) % alphabetPower;
            char newCharacter = (char) (startingCharacter + newAlphabetPosition);
            encrypted.append(newCharacter);
        }
        return encrypted.toString();
    }
}
