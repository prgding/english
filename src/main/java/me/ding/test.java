package me.ding;

import java.io.*;

public class test {
	public static void main(String[] args) {
		String fileName = "input.txt";
		int linesToRead = 5;

		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			int lineNumber = 0;
			while ((line = br.readLine()) != null && lineNumber < linesToRead) {
				System.out.println(line);
				lineNumber++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

