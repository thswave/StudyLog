package changwon.study.java;

import java.util.ArrayList;
import java.util.Scanner;

public class JavaBasic {
	public static void main(String[] args){
//		boolean f = true;
//		for (int i = 4; i < 100; i++) {
//			for (int j = 2; j <= Math.sqrt(i); j++) {
//				if ( i % j == 0){
//					f = false;
//					break;
//				}
//			}
//			if (f){
//				System.out.println(i);
//			}
//			f = true;
//		}
		eratos(10);
	}
	
	public static boolean isPalindrome(String str){
		int n = str.length();
		for (int i = 0; i < n/2 ; i++) {
			if (str.charAt(i) != str.charAt(n-1-i)){
				return false;
			}
		}
		return true;
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
}