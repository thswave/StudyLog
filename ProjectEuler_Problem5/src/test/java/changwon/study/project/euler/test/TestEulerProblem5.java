package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author changwonson
 *  1 ~ 10 사이의 어떤 수로도 나누어 떨어지는 가장 작은 수는 2520입니다.
 *  그러면 1 ~ 20 사이의 어떤 수로도 나누어 떨어지는 가장 작은 수는 얼마입니까?
 *
 */
public class TestEulerProblem5 {
	
	@Test
	public void testDivideByIntRagne(){
		LCM lcm = new LCM(1,20);
		assertEquals(232792560, lcm.getLCM());
		
	}
}
