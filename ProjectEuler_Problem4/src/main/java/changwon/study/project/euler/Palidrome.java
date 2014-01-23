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
		
		for (int i = getMaximumInteger(); i >= getMinimumInteger()  ; i--) {
			for (int j = getMaximumInteger() ; j >= getMinimumInteger() ; j--) {
				if (isPalidrome( i * j ) && maximumPalindrome < i * j ) {
					System.out.println("i : " + i + " j : "+ j + " // " + (i*j));
					maximumPalindrome = i * j;
				}
			}
		}
		
		return maximumPalindrome;
	}

	public int getMinimumInteger() {
		return (int) Math.pow(10, limitDigitCount - 1);
	}

	public int getMaximumInteger() {
		return (int) (Math.pow(10, limitDigitCount) - 1);
	}

	public boolean isPalidrome(long inputInteger) {
		return inputInteger == reverse(inputInteger);
	}

	private long reverse(long inputInteger) {
		long reverseInteger = 0;
		long matchInteger = inputInteger;
		while ( matchInteger != 0){
			reverseInteger = reverseInteger * 10 + matchInteger % 10;
			matchInteger /= 10;
		}
		return reverseInteger;
	}

}
