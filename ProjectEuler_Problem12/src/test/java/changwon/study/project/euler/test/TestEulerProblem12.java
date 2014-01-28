package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.TriangularNumber;

/**
 * @author changwonson
 *
 */
public class TestEulerProblem12 {
	
	@Test
	public void test(){
		TriangularNumber triangularNumber = new TriangularNumber();
		
		assertEquals(76576500,triangularNumber.getMinumumDivisor(500));
	}
}
