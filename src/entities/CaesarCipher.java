package entities;

public class CaesarCipher {

	static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

	public static String encriptUsingCaesarCipher(String input, int key) {

		String shiftedAlphabet = CaesarCipher.generateShiftedAlphabet(key);
		StringBuilder encrypted = new StringBuilder(input.toUpperCase());
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			int idx = ALPHABET.indexOf(ch);
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
		int key = 19;

		System.out.println("Alphabet........: " + ALPHABET);
		System.out.println("Shifted Alphabet: " + generateShiftedAlphabet(key));
		
		System.out.println();
		
		String text = "Paulo Alencar vai para a guerra com Julio Cesar";
		System.out.println("Text.....: " + text);
		System.out.println("Encrypted: " + encriptUsingCaesarCipher(text, key));
	}
}
