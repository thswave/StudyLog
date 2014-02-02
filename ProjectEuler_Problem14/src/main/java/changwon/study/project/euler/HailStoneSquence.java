package changwon.study.project.euler;

public class HailStoneSquence {

	private int limitValue;

	public HailStoneSquence(int limitValue) {
		this.limitValue = limitValue;
	}

	public long getMaximumCalculation() {
		long maxCalcCount = 0;
		long currentCalcCount = 0;
		for (int i = limitValue; i > 0; i--) {
			
			int hailstoneNumber = i;
			
			while (hailstoneNumber != 1){
				if (hailstoneNumber % 2 == 0){
					hailstoneNumber = evenCalculation(hailstoneNumber);
				} else {
					hailstoneNumber = oddCalculation(hailstoneNumber);
				}
				currentCalcCount++;
			}
			
			if (maxCalcCount < currentCalcCount) maxCalcCount = currentCalcCount;
		}
		return maxCalcCount;
	}

	private int oddCalculation(int n) {
		return 3 * n + 1;  
	}

	private int evenCalculation(int n) {
		return (n / 2);
	}

}
