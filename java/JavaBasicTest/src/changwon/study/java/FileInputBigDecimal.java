package changwon.study.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class FileInputBigDecimal {
	
	public static void main(String[] args){
		
		File file = new File("input_problem13");
		Scanner sc = null;
		BigDecimal decimal;
		BigDecimal sumDecimal = new BigDecimal(0);
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()){
				decimal = sc.nextBigDecimal();
				sumDecimal = sumDecimal.add(decimal);
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		System.out.println(sumDecimal);
	}
}
