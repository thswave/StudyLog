package changwon.study.project.euler.test;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import changwon.study.project.euler.IntegerFactorization;

/**
 * @author changwonson
 * 어떤 수를 소수의 곱으로만 나타내는 것을 소인수분해라 하고, 이 소수들을 그 수의 소인수라고 합니다.
 * 예를 들면 13195의 소인수는 5, 7, 13, 29 입니다.
 * 600851475143의 소인수 중에서 가장 큰 수를 구하세요.
 *
 */
@RunWith(value=Parameterized.class)
public class TestEulerProblem3 {
	
	private long initialInteger;
	private long expectMaximumPrime;
	private IntegerFactorization integerFactorization;

	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][]{
				{10, 5},
				{13195, 29},
				{600851475143L, 6857}
		});
	}
	
	public TestEulerProblem3(long initialInteger, long expectMaximumPrime){
		this.initialInteger = initialInteger;
		this.expectMaximumPrime = expectMaximumPrime;
	}
	
	@Before
	public void setUp(){
		integerFactorization = new IntegerFactorization(initialInteger);
		
	}

	@Test
	public void testIntegerFactorization(){
		
		assertEquals(expectMaximumPrime, integerFactorization.maximumPrimeNumber());
		
	}
}
