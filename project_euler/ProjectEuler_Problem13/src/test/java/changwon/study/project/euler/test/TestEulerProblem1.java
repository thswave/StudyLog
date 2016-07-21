package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.BigInt;

public class TestEulerProblem1 {
	
	@Test
	public void testSumOfBigDecimalFile(){
		BigInt bigInt = new BigInt("input_problem13");
		
		assertEquals(0L, bigInt.sumOfBigDecimalFile());
	}
}
