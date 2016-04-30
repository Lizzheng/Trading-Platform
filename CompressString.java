
public class CompressString {
	
	public static String compressString(String s){
		if(s.length() == 0){
			return s;
		}
		StringBuilder builder = new StringBuilder();
		int counter = 1;
		for(int i=1; i<s.length(); i++){
			
			if(s.charAt(i-1) == s.charAt(i)){
				counter++; 
				if(i == s.length()-1){
					builder.append(String.valueOf(s.charAt(i))+counter);
				}
			}else{
				builder.append(String.valueOf(s.charAt(i-1))+counter);
				counter = 1;
				if(i == s.length()-1){
					builder.append(String.valueOf(s.charAt(i))+counter);
				}
			}
		}
		
		if(builder.toString().length() < s.length())
			return builder.toString();
		return s;
	}

	public static void main(String[] args) {
		System.out.println(compressString("aabcccccaaab"));
	}

}
