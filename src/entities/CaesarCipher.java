package entities;

public class CaesarCipher {

	static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String encriptUsingCaesarCipher(String input, int key) {

		String shiftedAlphabet = CaesarCipher.generateShiftedAlphabet(key);
		StringBuilder encrypted = new StringBuilder(input);
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			int idx;
			
			if (Character.isLowerCase(ch)) {
				shiftedAlphabet = shiftedAlphabet.toLowerCase();
				idx = ALPHABET.toLowerCase().indexOf(ch);
			}
			else { 
				shiftedAlphabet = shiftedAlphabet.toUpperCase();
				idx = ALPHABET.indexOf(ch);
			}
			
			if (idx != -1) {
				encrypted.setCharAt(i, shiftedAlphabet.charAt(idx));
			}
		}
		return encrypted.toString();
	}
	
	public static String encriptUsingCaesarCipher(String input, int key1, int key2) {

		String shiftedAlphabet1 = CaesarCipher.generateShiftedAlphabet(key1);
		String shiftedAlphabet2 = CaesarCipher.generateShiftedAlphabet(key2);
		StringBuilder encrypted = new StringBuilder(input);
		String shiftedAlphabet;
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			int idx;
			
			if (i % 2 == 0) 
				shiftedAlphabet = shiftedAlphabet1;
			else 
				shiftedAlphabet = shiftedAlphabet2;
			
			if (Character.isLowerCase(ch)) {
				shiftedAlphabet = shiftedAlphabet.toLowerCase();
				idx = ALPHABET.toLowerCase().indexOf(ch);
			}
			else { 
				shiftedAlphabet = shiftedAlphabet.toUpperCase();
				idx = ALPHABET.indexOf(ch);
			}
			
			if (idx != -1) {
				encrypted.setCharAt(i, shiftedAlphabet.charAt(idx));
			}
		}
		return encrypted.toString();
	}
	
	public static final String generateShiftedAlphabet(int key) {
		String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
		return shiftedAlphabet;
	}

	public static void main(String[] args) {
		int key1 = 23;
		int key2 = 17;

		System.out.println("Alphabet...............: " + ALPHABET);
		System.out.println("Shifted Alphabet (key1): " + generateShiftedAlphabet(key1));
		System.out.println("----------------------------------");
		System.out.println("Alphabet...............: " + ALPHABET);
		System.out.println("Shifted Alphabet (key2): " + generateShiftedAlphabet(key2));
		
		System.out.println();
		
		String text = "First Legion";
		System.out.println("Text............: " + text);
		System.out.println("Encrypted (key1): " + encriptUsingCaesarCipher(text, key1));
		System.out.println("Encrypted (key1 e key2): " + encriptUsingCaesarCipher(text, key1, key2));
		
		System.out.println("=====================================================");
		String path = "/tmp/file";
		FileResource fr = new FileResource(path);
		String message = fr.asString();
		String encrypted = encriptUsingCaesarCipher(message, key1);
		System.out.println("key is " + key1 + "\n" + encrypted);
	}
}
