package entities;

public class CaesarCipher {

	private String shiftedAlphabet1;
	private String shiftedAlphabet2;
	private String alphabet;
	private int key1;
	private int key2;
	
	public CaesarCipher() {
		
	}
	
	public CaesarCipher(int key1) throws IllegalArgumentException {
		alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		setKey1(key1);
		shiftedAlphabet1 = generateShiftedAlphabet(key1);
	}
	
	public CaesarCipher(int key1, int key2) {
		this(key1);
		setKey2(key2);
		shiftedAlphabet2 = generateShiftedAlphabet(key2);
	}
	
	public String getShiftedAlphabet1() {
		return shiftedAlphabet1;
	}

	public String getShiftedAlphabet2() {
		return shiftedAlphabet2;
	}

	public String getAlphabet() {
		return alphabet;
	}

	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}

	public int getKey1() {
		return key1;
	}

	public void setKey1(int key1) {
		if (key1 > 0 && key1 < 26)
			this.key1 = key1;
		else 
			throw new IllegalArgumentException("Invalid informed key-1");
	}

	public int getKey2() {
		return key2;
	}

	public void setKey2(int key2) {
		if (key2 > 0 && key2 < 26)
			this.key2 = key2;
		else 
			throw new IllegalArgumentException("Invalid informed key-2");
	}
	
	// Main Methods:
	
	public String encrypt(String input) {

		StringBuilder encrypted = new StringBuilder(input);
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			int idx;
			
			if (Character.isLowerCase(ch)) {
				shiftedAlphabet1 = shiftedAlphabet1.toLowerCase();
				idx = alphabet.toLowerCase().indexOf(ch);
			}
			else { 
				shiftedAlphabet1 = shiftedAlphabet1.toUpperCase();
				idx = alphabet.indexOf(ch);
			}
			
			if (idx != -1) {
				encrypted.setCharAt(i, shiftedAlphabet1.charAt(idx));
			}
		}
		return encrypted.toString();
	}
	
	public String encryptTwoKeys(String input) {

		StringBuilder encrypted = new StringBuilder(input);
		String shiftedalphabet;
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			int idx;
			
			if (i % 2 == 0) 
				shiftedalphabet = shiftedAlphabet1;
			else 
				shiftedalphabet = shiftedAlphabet2;
			
			if (Character.isLowerCase(ch)) {
				shiftedalphabet = shiftedalphabet.toLowerCase();
				idx = alphabet.toLowerCase().indexOf(ch);
			}
			else { 
				shiftedalphabet = shiftedalphabet.toUpperCase();
				idx = alphabet.indexOf(ch);
			}
			
			if (idx != -1) {
				encrypted.setCharAt(i, shiftedalphabet.charAt(idx));
			}
		}
		return encrypted.toString();
	}
	
	public String decrypt(String encrypted) {
		int[] frequency = countFreqLetters(encrypted);
		int idx_larger = indexHighestFrequency(frequency);
		int dkey = idx_larger - 4;
		if (idx_larger < 4) {
			dkey = alphabet.length() - (4-idx_larger);
		}
		
		dkey = alphabet.length() - dkey;
		
		this.shiftedAlphabet1 = generateShiftedAlphabet(dkey);
		
		return this.encrypt(encrypted);
	}
	
	public String decryptTwoKeys(String encrypted) {
		String half1 = halfOfString(encrypted, 0);
		String half2 = halfOfString(encrypted, 1);
		
		int dkey1 = getKey(half1);
		int dkey2 = getKey(half2);
		
		System.out.println("dkey1 = " + dkey1);
		System.out.println("dkey2 = " + dkey2);
		
		this.shiftedAlphabet1 = generateShiftedAlphabet(dkey1);
		this.shiftedAlphabet2 = generateShiftedAlphabet(dkey2);
		
		return this.encryptTwoKeys(encrypted);
	}
	
	public void humanDecryptOneKey(String encrypted) {
		for (int key = 0; key < alphabet.length(); key++) {
			this.shiftedAlphabet1 = generateShiftedAlphabet(key);
			String output = this.encrypt(encrypted);
			System.out.println("key " + key + ": " + output);
		}
	}
	
	public String decodeOneKey(String encrypted) {
		this.shiftedAlphabet1 = generateShiftedAlphabet(alphabet.length() - this.key1); 
		return this.encrypt(encrypted);
	}
	
	// Helpers Methods
	
	private String generateShiftedAlphabet(int key) {
		String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
		return shiftedAlphabet;
	}
	
	private int[] countFreqLetters(String encrypted) {
		
		int[] counters = new int[alphabet.length()];
		
		for (int i = 0; i < encrypted.length(); i++) {
			char ch = encrypted.charAt(i);
			ch = Character.toUpperCase(ch);
			int idx = alphabet.indexOf(ch);
			if (idx != -1) 
				counters[idx] += 1;
		}
		return counters;
	}
	
	private int indexHighestFrequency(int[] frequency) {
		int larger = frequency[0];
		int idx_larger = 0;
		for (int i = 1; i < frequency.length; i++) {
			if (frequency[i] > larger) { 
				larger = frequency[i];
				idx_larger = i;
			}
		}
		return idx_larger;
	}
		
	private String halfOfString(String message, int start) {
		String half = "";
		for (int i = start; i < message.length(); i+=2) {
			half = half + message.charAt(i);
		}
		return half;
	}
	
	private int getKey(String s) {
		int[] frequency = countFreqLetters(s);
		int idx_larger = indexHighestFrequency(frequency);
		int dkey = idx_larger - 4;
		
		if (idx_larger < 4) {
			dkey = alphabet.length() - (4-idx_larger);
		}
		
		return alphabet.length() - dkey;
	}
	
}
