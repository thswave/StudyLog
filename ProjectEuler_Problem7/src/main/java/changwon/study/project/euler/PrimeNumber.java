package changwon.study.project.euler;

public class PrimeNumber {

	public long indexOf(int index) {
		long primeNumber = 4;
		int count = 2;
		boolean isPrimeNumber = true;
		while (true){
			for (int i = 2; i <= Math.sqrt(primeNumber); i++) {
				if (primeNumber % i == 0){
					isPrimeNumber = false;
					break;
				} 
			}
			if (isPrimeNumber) {
				count++;
			}
			if ( count == index){
				return primeNumber;
			}
			isPrimeNumber = true;
			if ( primeNumber % 2 != 0)
				primeNumber+=2;
			else 
				primeNumber++;
		}
	}

}
