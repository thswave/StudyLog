package changwon.study.project.euler;

public class Palidrome {

	private int limitDigitCount;

	public Palidrome(int limitDigitCount) {
		this.limitDigitCount = limitDigitCount;
	}

	public Palidrome() {
	}

	public long getMaximumPalindrome() {
		long maximumPalindrome = 0;
//		long firstLong = 0;
//		long secondLong = 0;
		for (int i = 999; i > 99 ; i--) {
			for (int j = 999; j > 99; j--) {
				if (isPalidrome( i * j ) && maximumPalindrome < i * j ) {
					System.out.println("i : " + i + " j : "+ j + " // " + (i*j));
					maximumPalindrome = i * j;
				}
			}
		}
		
//		isPalidrome();
		return maximumPalindrome;
	}

	public boolean isPalidrome(long inputInteger) {
		long initialInteger = inputInteger;
		
		StringBuilder sb = new StringBuilder();
		while( inputInteger > 10 ){
			sb.append( inputInteger % 10);
			inputInteger /= 10;
		}
		sb.append( inputInteger);
		
		return initialInteger == Long.parseLong(sb.toString());
	}

}
