package changwon.study.project.euler.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import changwon.study.project.euler.Multiple;

/**
 * @author changwonson 10보다 작은 자연수 중에서 3 또는 5의 배수는 3, 5, 6, 9 이고, 이것을 모두 더하면
 *         23입니다. 1000보다 작은 자연수 중에서 3 또는 5의 배수를 모두 더하면 얼마일까요?
 * 
 */
@RunWith(value=Parameterized.class)
public class TestEulerProblem1 {
	
	private int firstIntValue;
	private int secondIntValue;
	private int limitInt;
	private Multiple multiple ;
	private long expectationSum;

	public TestEulerProblem1(int firstIntValue, int secondIntValue,
			int limitValue, long expectationSum) {
		this.firstIntValue = firstIntValue;
		this.secondIntValue = secondIntValue;
		this.limitInt = limitValue;
		this.expectationSum = expectationSum;
	}

	@Parameters
	public static Collection<Object[]> setUpData() {
		return Arrays.asList(new Object[][] { 
				{ 3, 5, 1000, 233168}, 
				{ 3, 5, 10000, 23331668},
				{ 3, 5, 100000, 2333316668L}, 
				{ 3, 5, 1000000, 233333166668L} 
				});
	}
	
	@Before
	public void setUp() {
		List<Integer> intList = new ArrayList<Integer>();
		intList.add(firstIntValue);
		intList.add(secondIntValue);
		multiple = new Multiple(intList);
		multiple.setLimitInt(limitInt);
		
	}

	@Test
	public void testSumOfMutiplesUnderTen() {
		multiple.setLimitInt(10);
		assertEquals(23, multiple.sumOfMultiples()); // 3 6 9 5

	}

	@Test
	public void testSumOfMutiple() {	
		assertEquals("wrong result of sum expectation",	expectationSum, 
				multiple.sumOfMultiples());
	}
	
	@Test
	public void testSumOfMutiplesByFormula() {
		assertEquals("wrong result of sum expectation by fomula",	expectationSum, 
				multiple.sumOfMulplesByFormula());
	}

	@Test
	public void testSumTo100(){
		int sum = 0;
		for (int i = 1; i <= 10; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
