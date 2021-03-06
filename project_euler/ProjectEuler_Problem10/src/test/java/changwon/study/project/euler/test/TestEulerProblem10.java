package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.PrimeNumber;

/**
 * @author changwonson
 * 10 이하의 소수를 모두 더하면 2 + 3 + 5 + 7 = 17 이 됩니다.
 * 이백만(2,000,000) 이하 소수의 합은 얼마입니까?
 *
 */
public class TestEulerProblem10 {
	
	@Test
	public void testSumOfPrimeNumbers(){
		
		PrimeNumber primeNumber = new PrimeNumber(2000000);
		assertEquals(0, primeNumber.sumPrimeNumber());
	}
}
