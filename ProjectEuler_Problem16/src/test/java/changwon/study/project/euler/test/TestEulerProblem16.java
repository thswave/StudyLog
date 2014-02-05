package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.Exponential;

public class TestEulerProblem16 {
	
	@Test
	public void testExponential1000(){
		Exponential exponential  = new Exponential(1000);
		
		assertEquals(0L, exponential.getSumOfEachDigit());
	}
}
