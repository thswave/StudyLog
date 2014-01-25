package changwon.study.java;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileIOExample {
	public static void main(String[] args){
		
		int ch;
		
		String str = "49485\n8390";
		File file = new File("inputData");
		FileInputStream fis;
		
		
		try {
			fis = new FileInputStream(file);
			while ((ch = fis.read()) != -1){
				if (ch == '\n'){
					continue;
				}
				System.out.println((char)ch);
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
//		InputStream in = new ByteArrayInputStream(str.getBytes());
//		InputStreamReader isr = new InputStreamReader(str);
		
//		InputStream in = System.in;
		
//		try {
//			while ((ch = in.read()) != -1){
////				if (ch == 'S'){
////					in.skip(2);
////				}
//				System.out.println("char : " + (char)ch+" -> Available: "+in.available());
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
