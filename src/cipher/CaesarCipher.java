package cipher;

public class CaesarCipher implements CipherMethod<Integer> {
    @Override
    public String doEncryption(String message, Integer key) {
        final StringBuilder encrypted = new StringBuilder();
        final char startingCharacter = 'a';
        final int alphabetPower = 26;
        for (char character : message.toCharArray()) {
            final int originalAlphabetPosition = character - startingCharacter;
            final int newAlphabetPosition = (originalAlphabetPosition + key) % alphabetPower;
            final char newCharacter = (char) (startingCharacter + newAlphabetPosition);
            encrypted.append(newCharacter);
        }
        return encrypted.toString();
    }
}
