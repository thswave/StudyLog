package changwon.study.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileReadPerChar {
	public static void main(String[] args){
		File file = new File("input");
		FileInputStream fis;
		int ch;
		
		try {
			fis = new FileInputStream(file);
			while ((ch = fis.read()) != -1){
				if (ch == '\n'){
					continue;
				}
				System.out.print((char)ch);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
