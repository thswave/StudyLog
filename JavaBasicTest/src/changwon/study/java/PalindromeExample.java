package changwon.study.java;

public class PalindromeExample {
	
	public static void main(String[] args){
		System.out.println(isPalindrome("asdfasdf"));
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
}
