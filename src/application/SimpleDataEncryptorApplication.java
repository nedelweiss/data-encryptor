package application;

import encryptor.AesEncryptor;
import encryptor.BaseEncryptor;
import encryptor.VigenereEncryptor;

import java.util.Random;

public class SimpleDataEncryptorApplication {

    public static void main(String[] args) {
        // The main condition of Strategy Pattern: design pattern that enables selecting an algorithm at runtime
        final int randomNumber = new Random().nextInt(2);
        final BaseEncryptor<String> encryptor = randomNumber != 0
                ? new AesEncryptor()
                : new VigenereEncryptor();

        System.out.println(encryptor.encrypt("adfm", "diffusion"));
    }
}

