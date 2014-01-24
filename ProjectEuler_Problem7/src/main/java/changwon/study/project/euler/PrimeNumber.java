package changwon.study.project.euler;

public class PrimeNumber {

	public long indexOf(int index) {
		long primeNumber = 3;
		int count = 1;
		while (true){
			for (int i = 2; i < primeNumber; i++) {
				if (primeNumber % i == 0){
					break;
				} else if( i == primeNumber - 1){
					count++;
				}
			}
			
			if ( count == index){
				return primeNumber;
			}
			
			primeNumber++;
		}
	}

}
