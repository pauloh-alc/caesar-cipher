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

	public static final String generateShiftedAlphabet(int key) {
		String shiftedAlphabet = ALPHABET.substring(key) + ALPHABET.substring(0, key);
		return shiftedAlphabet;
	}

	public static void main(String[] args) {
		int key = 3;

		System.out.println("Alphabet........: " + ALPHABET);
		System.out.println("Shifted Alphabet: " + generateShiftedAlphabet(key));
		
		System.out.println();
		
		String text = "Paulo Henrique vai para guerra";
		System.out.println("Text.....: " + text);
		System.out.println("Encrypted: " + encriptUsingCaesarCipher(text, key));
	}
}
