package changwon.study.project.euler;

public class EulerProblem17 {

	private int[] letterCountArr;

	public EulerProblem17(int lastInteger) {
		this.letterCountArr = new int[lastInteger+1];
		
	}

	public int sumLetterCount() {
		init();
		for (int i = 21; i < letterCountArr.length-1; i++) {
			if (i / 100 != 0){
				if (i % 100 == 0){
					letterCountArr[i] += letterCountArr[i / 100];
					letterCountArr[i] += 7;					
					continue;
				} else{
					letterCountArr[i] += letterCountArr[i / 100];
					letterCountArr[i] += 10;
					letterCountArr[i] += letterCountArr[i % 100];
				}
			} else {
				if (i % 10 == 0) continue;
				letterCountArr[i] += letterCountArr[(i / 10) * 10];
				letterCountArr[i] += letterCountArr[i % 10];
			}
		}
		
		display(letterCountArr);
		return sum();
	}

	private int sum() {
		int sum = 0;
		for (int i = 0; i < letterCountArr.length; i++) {
			sum += letterCountArr[i];
		}
		return sum;
	}

	private void init() {
		letterCountArr[1] = 3; // one
		letterCountArr[2] = 3; // two 
		letterCountArr[3] = 5; // three 
		letterCountArr[4] = 4; // four 
		letterCountArr[5] = 4; // five 
		letterCountArr[6] = 3; // six
		letterCountArr[7] = 5; // seven 
		letterCountArr[8] = 5; // eight
		letterCountArr[9] = 4; // nine 
		letterCountArr[10] = 3; // ten
		letterCountArr[11] = 6; // eleven 
		letterCountArr[12] = 6; // twelve
		letterCountArr[13] = 8; // thirteen
		letterCountArr[14] = 8; // fourteen 
		letterCountArr[15] = 7; // fifteen
		letterCountArr[16] = 7; // sixteen
		letterCountArr[17] = 9; // seventeen 
		letterCountArr[18] = 8; // eighteen
		letterCountArr[19] = 8; // nineteen
		letterCountArr[20] = 6; // twenty 
		letterCountArr[30] = 6; // thirty 
		letterCountArr[40] = 5; // forty 
		letterCountArr[50] = 5; // fifty
		letterCountArr[60] = 5; // sixty 
		letterCountArr[70] = 7; // seventy 
		letterCountArr[80] = 6; // eighty
		letterCountArr[90] = 6; // ninety 
		letterCountArr[1000] = 11; // one thousand 
		
		// hundred and ..10
	}

	private void display(int[] letterCountArr) {
		for (int i = 1; i < letterCountArr.length; i++) {
			System.out.println(i+" : " +letterCountArr[i]);
		}
	}

}
