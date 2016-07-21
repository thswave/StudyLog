package changwon.study.project.euler;


public class TriangularNumber {

	public long getMinumumDivisor(int requestDivisorCount) {
		int triangularNumber = 0;
		int divisorCount = 0;
		for (int i = 1; divisorCount < requestDivisorCount ; i++ ){
			triangularNumber += i;
			divisorCount = getDivisorCount(triangularNumber);
		}
		
		return triangularNumber;
	}

	/**
	 * 
	 * 	약수 갯수 구하는 방
	 *  ex> 72 
	 *  소인수 분해 하면 2^^3 * 3^^2
	 *  각 항의 지수에 대해 +1 하여 서로 곱하면 약수의 개수
	 *  (3+1) * (2+1) = 12개!!
	 * 
	 *  약수의 합은 (2^^3 + 2^^2 + 2^^1 + 1) * (3^^2+ 2^^1) 
	 *  
	 *  
	 * @param triangularNumber
	 * @return divisorCount
	 */
	private int getDivisorCount(int triangularNumber) {
		int divisorCount = 1;
		int dividend = triangularNumber;
		int divisor = 2;
		int jisu = 1; 
		
		while (divisor <= dividend){
			if (dividend % divisor == 0){
				jisu++;
				dividend /= divisor;
			} else{
				divisorCount *= jisu;
				jisu = 1;
				divisor++;
			}
		}
		divisorCount *= jisu;
		
		return divisorCount ;
	}

}
