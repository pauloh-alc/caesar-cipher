package application;

import entities.CaesarCipher;
import entities.FileResource;

public class TestDescrypt {
	
	public static void showObject(CaesarCipher obj, String encrypted) {
		System.out.println("key1.............: " + obj.getKey1());
		if (obj.getKey2() != 0) System.out.println("key2.............: " + obj.getKey2());
		System.out.println("Message..........: " + encrypted);
		System.out.println("Alphabet.........: " + obj.getAlphabet());
		System.out.println("Shifited Alphabet: " + obj.getShiftedAlphabet1() + "\n");
	}
	
	public static void main(String[] args) {
		// Testing: Breaking the Caesar Cipher
		
		// With one key
		int key1 = 23;
		int key2 = 2;
		String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		
		CaesarCipher cipher1 = new CaesarCipher(key1);
		String encrypted = cipher1.encrypt(message);
		showObject(cipher1, encrypted);
		String output1 = cipher1.decrypt(encrypted);
		System.out.println("decrypted.........: " + output1);
		
		System.out.println("===========================================");
		
		// With two keys
		CaesarCipher cipher2 = new CaesarCipher(key1, key2);
		encrypted = cipher2.encryptTwoKeys(message);
		showObject(cipher2, encrypted);
		String output2 = cipher1.decryptTwoKeys(encrypted);
		System.out.println("decrypted.........: " + output2);
		
		System.out.println("=====================================================");
		
		System.out.println("The following phrase was encrypted with the two key: ");
		String decrypted  = cipher2.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
		System.out.println(decrypted);
		
		System.out.println("=====================================================");
		
		System.out.println("Decrypt the encrypted file mysteryTwoKeysPractice.txt: ");
		FileResource resource = new FileResource("../caesar-cipher/files/mysteryTwoKeysPractice.txt");
		String mystery = cipher2.decryptTwoKeys(resource.asString());
		System.out.println(mystery);
	}
}
