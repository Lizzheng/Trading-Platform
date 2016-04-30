
public class StringPermutation {
	
	public static boolean isPermutation(String s1, String s2){
		
		if (s1.length() != s2.length()){
			return false;
		}
		
		int[] stringOne = new int[s1.length()]	;
		int[] stringTwo = new int[s2.length()]	;
		
		for(int i=0; i<s1.length(); i++){
			stringOne[i] = Character.getNumericValue(s1.charAt(i));
		}
		
		for(int i=0; i<s2.length(); i++){
			stringTwo[i] = Character.getNumericValue(s2.charAt(i));
		}
		
		for(int i=1; i<stringOne.length; i++){
			for(int j=i ; j>0; j--){
				if(stringOne[j]<stringOne[j-1]){
					int temp = stringOne[j-1]; 
					stringOne[j-1] = stringOne[j]; 
					stringOne[j] = temp;
				}
			}
		}
		
		for(int i=1; i<stringTwo.length; i++){
			for(int j=i ; j>0; j--){
				if(stringTwo[j]<stringTwo[j-1]){
					int temp = stringTwo[j-1]; 
					stringTwo[j-1] = stringTwo[j]; 
					stringTwo[j] = temp;
				}
			}
		}
		
		for(int i=0; i<stringOne.length; i++) {
			if(stringOne[i] != stringTwo[i])
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPermutation("dab", "dat"));
		System.out.println(isPermutation("abc", "bca"));
		System.out.println(isPermutation("aaa", "aba"));
	}

}
