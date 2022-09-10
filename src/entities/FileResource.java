package entities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileResource {
	
	private final String PATH;
	
	public FileResource(String path) {
		this.PATH = path;
	}
	
	public String getPath() {
		return PATH;
	}
	
	public String asString() {
		String text = "";
		try (BufferedReader bf = new BufferedReader(new FileReader(PATH))) {
			String line = bf.readLine();
			while (line != null) {
				text += line;
				line = bf.readLine();
			}
		}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return text;
	}
}
