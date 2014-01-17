package changwon.study.project.euler;

import java.util.List;

public class Multiple {

	private int limitInt;	
	private List<Integer> intList;

	public Multiple() {
	}

	public Multiple(List<Integer> intList) {
		this.intList = intList;
	}

	
	public long sumOfMultiples() {
		long sumOfMultiples = 0;
		
		for (int i = 0; i < limitInt; i++) {
			if ( isMutiple(i)){
				sumOfMultiples += i; 
			}
		}
		
		return sumOfMultiples;
	}

	private boolean isMutiple(int value) {
		for (Integer divider : intList) {
			if (value % divider == 0) return true; 
		}
		return false;
	}
	
	private long getMultipleSum(long number) {
		long multipleNum	= 0;

		if(1000 % number == 0)
			multipleNum = limitInt / number -1;
		else
			multipleNum = limitInt / number;

		return multipleNum*(2*number + (multipleNum-1)*number);
	}

	public long sumOfMulplesByFormula() {
		long sumOfMultiple = 0;	
		sumOfMultiple += getMultipleSum(this.intList.get(0));
		sumOfMultiple += getMultipleSum(this.intList.get(1));
		sumOfMultiple -= getMultipleSum(15);
		sumOfMultiple /= 2;
		
		return sumOfMultiple;
	}
	
	public int getLimitInt() {	return limitInt; }
	public void setLimitInt(int limitInt) {	this.limitInt = limitInt;}
	public List<Integer> getIntList() {	return intList;	}
}
