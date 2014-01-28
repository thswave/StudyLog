package changwon.study.project.euler;

import java.util.HashMap;
import java.util.Iterator;

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
		HashMap<Integer,Integer> divisorList = new HashMap<Integer,Integer>();
		
		int divisor = 2;
		while (divisor <= dividend){
			if (dividend % divisor == 0){
				if (divisorList.containsKey(divisor)){
					divisorList.put(divisor, divisorList.get(divisor) + 1);
				} else {
					divisorList.put(divisor, 1);
				}
				dividend /= divisor;
			} else{
				divisor++;
			}
		}
		Iterator<Integer> itr = divisorList.keySet().iterator();
		while (itr.hasNext()){
			divisorCount *= divisorList.get(itr.next()) + 1;
		}
		return divisorCount ;
	}

}
