package changwon.study.project.euler;

public class PrimeNumber {

	private int limit;

	public PrimeNumber(int limit) {
		this.limit = limit;
	}

	public long sumPrimeNumber() {
		
		boolean[] eratos = new boolean[limit+1];
		long sum = 0;
		
		for (int i = 0; i <= limit; i++) { eratos[i] = true;	}
		eratos[0] = false;
		eratos[1] = false;
		
		for (int i = 2 ; i * i <= limit; i++) {
			for (int j = i*i ; j <= limit; j += i ) {
				eratos[j] = false;
			}
		}
		
		for (int i = 2; i <= limit; i++) {
			if (eratos[i] == true) sum += i;
		}
		
		
		return sum;
	}
	
	

}
