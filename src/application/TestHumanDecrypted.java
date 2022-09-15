package application;

import entities.CaesarCipher;

public class TestHumanDecrypted {

	public static void main(String[] args) {
		int key1 = 23;
		String text = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		CaesarCipher cipher1 = new CaesarCipher(key1);
		System.out.println("Human Decrypted Message: ");
		String encrypted = cipher1.encrypt(text);
		cipher1.humanDecryptOneKey(encrypted);
	}

}
