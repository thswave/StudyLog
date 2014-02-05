package changwon.study.project.euler;

import java.util.ArrayList;

public class HailStoneSquence {

	private int limitValue;
	private ArrayList<Long> calcCountArr;

	public HailStoneSquence(int limitValue) {
		this.limitValue = limitValue;
		calcCountArr = new ArrayList<Long>(limitValue);
	}

	public long getMaximumCalculation() {
		long maxCalcCount = 0;
		int maxCalcValue = 0;
		calcCountArr.add(0L);
		calcCountArr.add(0L);
		calcCountArr.add(1L);
		
		for (int i = 3; i <= limitValue; i++) {
			long hailstoneNumber = i;
			long currentCalcCount = 0;
//			System.out.println(hailstoneNumber);
			while (hailstoneNumber != 1){
				if (hailstoneNumber < i){
//					System.out.println(hailstoneNumber);
					currentCalcCount += calcCountArr.get((int) hailstoneNumber);
					hailstoneNumber = 1;
				}
				else if (hailstoneNumber % 2 == 0){
					hailstoneNumber = hailstoneNumber >> 1;
					currentCalcCount++;
				} else {
					hailstoneNumber = 3 * hailstoneNumber + 1;
					currentCalcCount++;
				}
				
				if (hailstoneNumber == 1) calcCountArr.add(currentCalcCount);
			}
			
			if (maxCalcCount < currentCalcCount) {
				maxCalcCount = currentCalcCount;
				maxCalcValue = i;
			}
		}
//		display(calcCountArr);
		
		return maxCalcValue;
	}

	private void display(ArrayList<Long> calcCountArr) {
		for (int i = 900000; i < calcCountArr.size(); i++) {
			System.out.println(i+ " : " +calcCountArr.get(i));
		}
		
	}

	private long oddCalculation(long n) {
		return 3 * n + 1;  
	}

	private int evenCalculation(long n) {
		return (int) (n / 2);
	}

}


/*
import java.util.ArrayList;

public class Sequence {
 
    private ArrayList<Long> sequenceList;
    private long num = 0, size = 0, largest = 0;
 
 
    public void largestSequence(long listToTry) {
        for (int i = 1; i <= listToTry; i++) {
            getSequence(i, false);
            checkIfLargest();
 
        }
        getSequence(largest);
        System.out.println("The largest sequence is from number " + largest);
    }
 
    public void getSequence(long seqNum) {
        getSequence(seqNum, true);
    }
    private void getSequence(long seqNum, boolean display) {
 
        sequenceList = new ArrayList<Long>();
        sequenceList.add(seqNum);
        this.num = seqNum;
        do {
            if (this.num % 2 == 0) {
                isEven(this.num);
            } else {
                isOdd(this.num);
            }
        } while (sequenceList.get(sequenceList.size()-1) != 1);
 
        if (display) {
            displayInfo();
        }
    }
 
    //Odd Sequence calculation
    private void isOdd(long num) {
        this.num = 3 * num + 1;
        sequenceList.add(this.num);
    }
    //Even Sequence calculation
    private void isEven(long num) {
        this.num = num / 2;
        sequenceList.add(this.num);
    }
 
 
    // checking and displaying
    private void checkIfLargest() {
        if (sequenceList.size() > size) {
            size = sequenceList.size();
            largest = sequenceList.get(0);
        }
    }
    private void displayInfo(){
        System.out.println(sequenceList);
        System.out.println("With a length of " + sequenceList.size());
    }
 
}
 
 
//.......................................
//main method!!
class SequenceTest {
    public static void main(String[] args) {
        //StringSequence test = new StringSequence();
        Sequence test = new Sequence();
        test.largestSequence(1000000);
    }
}

*/