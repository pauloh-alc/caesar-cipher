package entities;

import java.util.Scanner;

public class CaesarCipher {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
		System.out.print("key: ");
		Integer key = sc.nextInt();
		
		String slice = alphabet.substring(key); 
		String shiftedAlphabet = slice + alphabet.substring(0, key);
		
		System.out.println("Alphabet........: " + alphabet);
		System.out.println("Shifted Alphabet: " + shiftedAlphabet);
		
		sc.close();
	}
}
