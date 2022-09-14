package application;

import entities.CaesarCipher;
import entities.FileResource;

public class TestEncrypt {
	
	public static void showObject(CaesarCipher obj, String encrypted) {
		System.out.println("key1.............: " + obj.getKey1());
		if (obj.getKey2() != 0) System.out.println("key2.............: " + obj.getKey2());
		System.out.println("Message..........: " + encrypted);
		System.out.println("Alphabet.........: " + obj.getAlphabet());
		System.out.println("Shifited Alphabet: " + obj.getShiftedAlphabet1() + "\n");
	}
	
	public static void main(String[] args) {
		// Testing: Implementing the Caesar Cipher
		
		// With one key
		int key1 = 23;
		int key2 = 17;
		String path = "../caesar-cipher/files/message1.txt";
		FileResource fileRecourse = new FileResource(path);
		String message = fileRecourse.asString();
		
		CaesarCipher cipher1 = new CaesarCipher(key1);
		String encrypted = cipher1.encrypt(message);
		
		showObject(cipher1, encrypted);
		
		System.out.println("===========================================");
		
		// With two keys
		CaesarCipher cipher2 = new CaesarCipher(key1, key2);
		encrypted = cipher2.encryptTwoKeys(message);
		showObject(cipher2, encrypted);
	}

}
