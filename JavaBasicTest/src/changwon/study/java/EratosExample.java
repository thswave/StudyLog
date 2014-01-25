package changwon.study.java;

import java.util.ArrayList;

public class EratosExample {
	public static void main(String[] args){
//		eratos(100);
		
		System.out.println(getPrimeNumberByIndex(5));
	}
	
	public static void eratos(int n) {
		// ArrayList로 구현
		ArrayList<Boolean> primeList;
 
		// 사용자로부터의 콘솔 입력
//		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
 
		// n <= 1 일 때 종료 
		if(n <= 1) return;
 
		// n+1만큼 할당
		primeList = new ArrayList<Boolean>(n+1);
		// 0번째와 1번째를 소수 아님으로 처리 
		primeList.add(false);
		primeList.add(false);
		// 2~ n 까지 소수로 설정
		for(int i=2; i<=n; i++ )
			primeList.add(i, true);
 
		// 2 부터  ~ i*i <= n
		// 각각의 배수들을 지워간다.
		for(int i=2; (i*i)<=n; i++){
			if(primeList.get(i)){
				for(int j = i*i; j<=n; j+=i) primeList.set(j, false);
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for(int i=0; i<=n; i++){
			if(primeList.get(i) == true){
				sb.append(i);
				sb.append(",");
			}
		}
		sb.setCharAt(sb.length()-1, '}');
 
		String str = new String(sb);
		System.out.println(str);
 
	}
	
	public static long getPrimeNumberByIndex(long index){
		int count = 2;
		long primeNumber = 4;
		boolean isPrimeNumber = true;
		
		if( index == 1) return 2;
		else if( index == 2) return 3;
		
		while(true){
			for (int i = 2; i <= Math.sqrt(primeNumber); i++) {
				if ( primeNumber % i == 0 ){
					isPrimeNumber = false;
					break;
				}
			}
			if (isPrimeNumber){
				count++;
			}
			if (index == count){
				return primeNumber;
			}
			isPrimeNumber = true;
			primeNumber++;
		}
	}
}
