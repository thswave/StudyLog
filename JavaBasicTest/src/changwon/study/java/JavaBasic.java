package changwon.study.java;

public class JavaBasic {
	public static void main(String[] args){
		System.out.println("hello");
		
		System.out.println(isPalindrome("asdfdsa"));
		Integer a = new Integer(1);
		
//		System.out.println(a.);
		StringBuilder sb = new StringBuilder();
		sb.append("asdfdsaaa");
		System.out.println(sb.toString());
		System.out.println(sb.reverse());
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
	public static boolean isPalindrome(int number){
		return reverse(number);
	}

	private static boolean reverse(int number) {
		
		return false;
	}
}
