package changwon.study.project.euler;

public class Pitagoras {

	public long productPitagoras() {
		int k;
		for (int i = 1; i < 1000; i++) {
			for (int j = 1; j < 1000 - i ; j++) {
				k = 1000 - i - j;
				if (i + j + k != 1000 || k <= j || j <= i){
					continue;
				}
				if ( (int)(Math.pow(i, 2)) + (int)(Math.pow(j, 2)) == (int)(Math.pow(k, 2))){
					System.out.print(" i :"+ i);
					System.out.print(" j :"+ j);
					System.out.println(" k :"+ k);
					return i * j * k;
				}
			}
		}
		return 0;
	}

}
