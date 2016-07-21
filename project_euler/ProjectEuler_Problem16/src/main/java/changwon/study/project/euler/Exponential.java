package changwon.study.project.euler;

import java.math.BigDecimal;

public class Exponential {

	private int exponentialValue;

	public Exponential(int exponentialValue) {
		this.exponentialValue = exponentialValue;
	}

	public long getSumOfEachDigit() {
		BigDecimal exp = BigDecimal.ONE;
		BigDecimal two = new BigDecimal(2);
		long sum = 0;
		for (int i = 1; i <= exponentialValue; i++) {
			exp = exp.multiply(two);
		}
		byte[] b = exp.toString().getBytes();
		for (int i = 0; i < b.length; i++) {
			sum += Integer.parseInt(String.valueOf((char)b[i]));
		}
		
		return sum;
	}
	

}
