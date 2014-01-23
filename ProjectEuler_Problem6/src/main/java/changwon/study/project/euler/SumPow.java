package changwon.study.project.euler;

public class SumPow {

	private int startInt;
	private int endInt;

	public SumPow(int startInt, int endInt) {
		this.startInt = startInt;
		this.endInt = endInt;
	}

	public long getDiffereceSumPow() {
		return getPowerOfSum() - getSumOfPower();
	}

	private long getSumOfPower() {
		int sum = 0;
		for (int i = startInt; i <= endInt; i++) {
			sum += Math.pow(i, 2);
		}
		return sum;
	}

	private long getPowerOfSum() {
		int sum = 0;
		for (int i = startInt; i <= endInt; i++) {
			sum += i;
		}
		return (long) Math.pow(sum, 2);
	}

}
