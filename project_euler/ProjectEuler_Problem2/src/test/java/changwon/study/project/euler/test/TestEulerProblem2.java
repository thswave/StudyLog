package changwon.study.project.euler.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import changwon.study.project.euler.Fibo;

/**
 * @author changwonson
 * 피보나치 수열의 각 항은 바로 앞의 항 두 개를 더한 것이 됩니다. 1과 2로 시작하는 경우 이 수열은 아래와 같습니다.
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * 짝수이면서 4백만 이하인 모든 항을 더하면 얼마가 됩니까?
 *
 */
public class TestEulerProblem2 {
	
	@Test
	public void testFibo(){
		Fibo fibo = new Fibo();
		fibo.setLimit(4000000000L);
		assertEquals(4613732, fibo.sumFibonacciSequence());
		
	}
}
