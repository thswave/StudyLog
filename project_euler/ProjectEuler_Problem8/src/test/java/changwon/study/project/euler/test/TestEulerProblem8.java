package changwon.study.project.euler.test;

import static org.junit.Assert.*;

import org.junit.Test;

import changwon.study.project.euler.ProductDigit;

/**
 * @author changwonson
 * 연속된 1000자리 숫자에서 5자리 숫자의 곱 중에서 가장 큰 값은 얼마입니까?
 * 입력 데이터 파일명 : inputData
 */
public class TestEulerProblem8 {

	@Test
	public void testMaximumProduct(){
		ProductDigit productDigit = new ProductDigit("inputData");
		
		assertEquals(40824, productDigit.getMaximumProduct());
	}
}
