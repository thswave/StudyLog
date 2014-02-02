package changwon.study.project.euler;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class BigInt {

	private String fileName;

	public BigInt(String fileName) {
		this.fileName = fileName;
	}

	@SuppressWarnings("resource")
	public long sumOfBigDecimalFile() {
		
		File file = new File(fileName);
		Scanner sc = null;
		BigDecimal decimal;
		BigDecimal sumDecimal = new BigDecimal(0);
		long sum = 0;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()){
				decimal = sc.nextBigDecimal();
				sumDecimal = sumDecimal.add(decimal);
			}
			
			char[] chArr = sumDecimal.toString().toCharArray();
			for (int i = 0; i <= 10; i++) {
				System.out.println( sum);
				sum = sum * 10 + Integer.parseInt(String.valueOf(chArr[i]));
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}		
		
		
		return sum;
	}

}
