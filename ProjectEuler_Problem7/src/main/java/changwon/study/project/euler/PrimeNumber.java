package changwon.study.project.euler;

public class PrimeNumber {

	public long indexOf(int index) {
		long primeNumber = 2;
		int count = 0;
		while (true){
			for (int i = 2; i <= Math.sqrt(primeNumber); i++) {
				if (primeNumber % i == 0){
					break;
				} 
				count++;
			}
			
			if ( count == index){
				return primeNumber;
			}
			
			primeNumber++;
		}
	}

}
