package changwon.study.project.euler;

public class IntegerFactorization {

	private long initialInteger;

	public IntegerFactorization(long initialInteger) {
		this.initialInteger = initialInteger;
	}

	public long maximumPrimeNumber() {
		long maxPrimeNumber = 2;
		long testInteger = this.initialInteger;
		
		while(true) {
			if (testInteger % maxPrimeNumber == 0){
				testInteger /= maxPrimeNumber;
			} else {
				maxPrimeNumber++;
			}
			
			if (maxPrimeNumber == testInteger ){
				break;
			}
		}
		
		return maxPrimeNumber;
	}

}
