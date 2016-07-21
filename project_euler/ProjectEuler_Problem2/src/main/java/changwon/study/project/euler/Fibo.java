package changwon.study.project.euler;

public class Fibo {

	private long limit;

	public void setLimit(long limit) {
		this.limit = limit;
	}

	public long sumFibonacciSequence() {
		long firstNumber = 0;
		long secondNumber = 1;
		long thirdNumber = 0;
		long sum = 0;
		
		while ( secondNumber <= limit){
			if (isRemainderZeroByDivideTwo(secondNumber) ){
				sum += secondNumber;
			}
			thirdNumber = firstNumber;
			firstNumber = secondNumber;
			secondNumber = thirdNumber + secondNumber;
			
		}

		return sum;
	}

	public boolean isRemainderZeroByDivideTwo(long secondNumber) {
		return (secondNumber % 2) == 0;
	}

}
