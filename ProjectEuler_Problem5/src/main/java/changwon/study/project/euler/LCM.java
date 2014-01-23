package changwon.study.project.euler;

public class LCM {

	private int startInt;
	private int endInt;

	public LCM(int startInt, int endInt) {
		this.startInt = startInt;
		this.endInt = endInt;
	}

	public long getLCM() {
		long lcm = 1;
		while (true) {
			for (int i = startInt; i <= endInt; i++) {
				if ( lcm % i != 0){
					break;
				}
				
				if ( i == endInt){
					return lcm;
				}
			}
			lcm++;
		}
	}
	
	

}
