package application;

import entities.CaesarCipher;
import entities.FileResource;

public class Test {
	
	public static void main(String[] args) {
		int key1 = 2;
		int key2 = 20;
		
		CaesarCipher c1 = new CaesarCipher();

		System.out.println("Alphabet...............: " + c1.getALPHABET());
		System.out.println("Shifted Alphabet (key1): " + c1.generateShiftedAlphabet(key1));
		System.out.println("----------------------------------");
		System.out.println("Alphabet...............: " + c1.getALPHABET());
		System.out.println("Shifted Alphabet (key2): " + c1.generateShiftedAlphabet(key2));
		
		System.out.println();
		
		String text = "Just a test string with lots of eeeeeeeeeeeeeeeees";
		System.out.println("Text............: " + text);
		System.out.println("Encrypted (key1): " + c1.encriptUsingCaesarCipher(text, key1));
		System.out.println("Encrypted (key1 e key2): " + c1.encriptUsingCaesarCipher(text, key1, key2));
		
		System.out.println("=====================================================");
		String path = "/tmp/file";
		FileResource fr = new FileResource(path);
		String message = fr.asString();
		String encrypted = c1.encriptUsingCaesarCipher(message, key1);
		System.out.println("key is " + key1 + "\n" + encrypted);
		
		System.out.println("=====================================================");
		System.out.println("Human Decrypted Message: ");
		encrypted = c1.encriptUsingCaesarCipher(text, key1);
		c1.humanDecrypt(encrypted);
		
		System.out.println("=====================================================");
		System.out.println("encrypeted: " + encrypted);
		int[] x = c1.countFreqLetters(encrypted);
		for (int i = 0; i < x.length; i++) {
			System.out.println(c1.getALPHABET().charAt(i) + ": " + x[i]);
		}
		
		System.out.println("=====================================================");
		String output = c1.decrypt(encrypted);
		System.out.println("Message: " + output);
		
		System.out.println("=====================================================");
		output = c1.decode(encrypted, key1);
		System.out.println("Message: " + output);
		
		System.out.println("=====================================================");
		System.out.println(c1.halfOfString("Qbkm Zgis", 0));
		System.out.println(c1.halfOfString("Qbkm Zgis", 1));
		
		System.out.println("=====================================================");
		String s = c1.decryptTwoKeys(c1.encriptUsingCaesarCipher(text, key1, key2));
		System.out.println(s);
		
		System.out.println("=====================================================");
		System.out.println("The following phrase was encrypted with the two key: ");
		s = c1.decryptTwoKeys("Akag tjw Xibhr awoa aoee xakex znxag xwko");
		System.out.println(s);
		
		System.out.println("=====================================================");
		System.out.println("Decrypt the encrypted file mysteryTwoKeysPractice.txt: ");
		FileResource resource = new FileResource("../caesar-cipher/files/mysteryTwoKeysPractice.txt");
		String mystery = c1.decryptTwoKeys(resource.asString());
		System.out.println(mystery);
	
	}
}
