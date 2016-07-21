package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.EulerProblem17;

public class TestEulerProblem17 {
	
	@Test
	public void test(){
		EulerProblem17 euler17 = new EulerProblem17(1000);
		assertEquals(21124, euler17.sumLetterCount());
	}
}
