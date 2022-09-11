package entities;

public class CaesarCipher {

	private final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public CaesarCipher() {
	
	}
	
	public String getALPHABET() {
		return ALPHABET;
	}
	
	/*
	 * Implementing the Caesar Cipher
	 */
	public String encriptUsingCaesarCipher(String input, int key) {

		String shiftedAlphabet = generateShiftedAlphabet(key);
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
	
	public String encriptUsingCaesarCipher(String input, int key1, int key2) {

		String shiftedAlphabet1 = generateShiftedAlphabet(key1);
		String shiftedAlphabet2 = generateShiftedAlphabet(key2);
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
	
	public final String generateShiftedAlphabet(int key) {
		String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
		return shiftedAlphabet;
	}
	
	/*
	 * Breaking the Caesar Cipher
	 */
	
	// Abordagem - Human Decryption
	
	public void humanDecrypt(String encrypted) {
		for (int key = 0; key < ALPHABET.length(); key++) {
			String output = this.encriptUsingCaesarCipher(encrypted, key);
			System.out.println("key " + key + ": " + output);
		}
	}
}
