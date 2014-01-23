package changwon.study.project.euler;

public class Palidrome {

	private int limitDigitCount = 0;

	public Palidrome(int limitDigitCount) {
		this.limitDigitCount = limitDigitCount;
	}

	public Palidrome() {
	}

	public long getMaximumPalindrome() {
		long maximumPalindrome = 0;
//		long firstLong = 0;
//		long secondLong = 0;
		
		for (int i = (int) (Math.pow(10, limitDigitCount) - 1); i >= Math.pow(10, limitDigitCount - 1)  ; i--) {
			for (int j = (int) (Math.pow(10, limitDigitCount) - 1) ; j >= Math.pow(10, limitDigitCount - 1) ; j--) {
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
