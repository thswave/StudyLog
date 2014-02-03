package changwon.study.project.euler;

public class HailStoneSquence {

	private int limitValue;
	private int[] calcCountArr;

	public HailStoneSquence(int limitValue) {
		this.limitValue = limitValue;
		calcCountArr = new int[limitValue * 10];
	}

	public long getMaximumCalculation() {
		init();
		long maxCalcCount = 0;
		long currentCalcCount = 0;
//		for (int i = 2; i < limitValue; i++) {
//			
//			int hailstoneNumber = i;
//			
//			while (hailstoneNumber != 1){
//				if (hailstoneNumber % 2 == 0){
//					hailstoneNumber = evenCalculation(hailstoneNumber);
//				} else {
//					hailstoneNumber = oddCalculation(hailstoneNumber);
//				}
//				currentCalcCount++;
//			}
//			
//			if (maxCalcCount < currentCalcCount) maxCalcCount = currentCalcCount;
//		}
		
		for (int i = 2; i < limitValue; i++) {
			int hailstoneNumber = i;
			while (hailstoneNumber != 1){
				
//				 = currentCalcCount;
			}
		}
		
		
		
		return maxCalcCount;
	}

	private void init() {
		int count = 1;
		for (int i = 2; i < calcCountArr.length; i*=2) {
			calcCountArr[i] = count++; 
		}
		
	}

	private int oddCalculation(int n) {
		return 3 * n + 1;  
	}

	private int evenCalculation(int n) {
		return (n / 2);
	}

}
