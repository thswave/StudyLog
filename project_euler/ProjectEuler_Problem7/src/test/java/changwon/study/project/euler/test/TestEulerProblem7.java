package changwon.study.project.euler.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import changwon.study.project.euler.PrimeNumber;

/**
 * @author changwonson
 * 소수를 크기 순으로 나열하면 2, 3, 5, 7, 11, 13, ... 과 같이 됩니다.
 * 이 때 10,001번째의 소수를 구하세요.
 *
 */
public class TestEulerProblem7 {
	
	@Test
	public void testPrimeNumber(){
		PrimeNumber primeNumber = new PrimeNumber();
		
		assertEquals(104743, primeNumber.indexOf(10001));
	}
}
