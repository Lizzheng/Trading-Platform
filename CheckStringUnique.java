
public class CheckStringUnique {
	
	public static boolean isUnique(String s){
		int length = s.length();
		if (length == 0 || length == 1)
			return true;
		char first = s.charAt(0); 
		for(int i=1; i < s.length(); i++){
		if (first == s.charAt(i))
			return false;
		}
		return isUnique(s.substring(1, s.length()));
		}

	public static void main(String[] args) {
		System.out.println(isUnique("blalahbh")); 
		System.out.println(isUnique("abcdef")); 

	}

}
