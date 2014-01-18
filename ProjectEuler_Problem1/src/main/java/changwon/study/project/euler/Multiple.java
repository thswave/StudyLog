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
	
	public long getMultipleSum(long firstNumber) {
		long termCount	= 0;
		long lastNumber;

		termCount = getTermCountExcludeLast(firstNumber);
		lastNumber = firstNumber * termCount;
		return (termCount*(firstNumber + lastNumber) ) / 2;
	}

	public long getTermCountExcludeLast(long number) {
		if(1000 % number == 0)
			return limitInt / number -1;
		else
			return limitInt / number;
	}

	public long sumOfMulplesByFormula() {
		long sumOfMultiple = 0;	
		sumOfMultiple += getMultipleSum(this.intList.get(0));
		sumOfMultiple += getMultipleSum(this.intList.get(1));
		sumOfMultiple -= getMultipleSum(15);
		
		return sumOfMultiple;
	}
	
	public void setIntList(List<Integer> intList) {this.intList = intList;}
	public int getLimitInt() {	return limitInt; }
	public void setLimitInt(int limitInt) {	this.limitInt = limitInt;}
	public List<Integer> getIntList() {	return intList;	}
}
