package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.HailStoneSquence;

public class TestEulerProblem14 {
	
	@Test
	public void testCountOfCalculation(){
		HailStoneSquence hailStoneSquence = new HailStoneSquence(1000000);
		
		assertEquals(847799, hailStoneSquence.getMaximumCalculation());
	}
}
