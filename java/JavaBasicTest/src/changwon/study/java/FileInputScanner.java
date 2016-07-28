package changwon.study.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileInputScanner {
	
//	final static Logger logger = LoggerFactory.getLogger(FileInputScanner.class);
	
	public static void main(String[] args){
		int[][]list =new int[20][20];
		
		Scanner sc = null;
		
		try {
			sc = new Scanner(new File("input"));
			
			for (int i = 0; i < 20; i++) {
				for (int j = 0; j < 20; j++) {
					list[i][j] = sc.nextInt();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			StackTraceElement[] stck = e.getStackTrace();
			String className = stck[0].getClassName();
			String methodName = stck[0].getMethodName();
			int lineNumber = stck[0].getLineNumber();
			String fileName = stck[0].getFileName();
			
//			logger.error("Exception : " + e.getMessage());
//			logger.error(className+"."+methodName+"."+fileName+" line "+lineNumber);
			
		}
	}
}
