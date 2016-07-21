package changwon.study.project.euler.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import changwon.study.project.euler.PathCase;

public class TestEulerProblem15 {
	
	@Test
	public void testPathCase(){
		PathCase pathCase = new PathCase(20);
		
		assertEquals(137846528820L, pathCase.getPathCase());
	}
}
