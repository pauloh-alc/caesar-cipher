package entities;

public class CaesarCipher {
	
	static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static final String generateShiftedAlphabet(int key) {
		String slice = ALPHABET.substring(key); 
		String shiftedAlphabet = slice + ALPHABET.substring(0, key);
		
		return shiftedAlphabet;
	}
	
	public static void main(String[] args) {
		System.out.println(ALPHABET);
		System.out.println(generateShiftedAlphabet(23));
	}
}
