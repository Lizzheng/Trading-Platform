
public class ReplaceSpace {
	
	public static String replaceSpace(String s){
		String str = ""; 
		
		for(int i=0; i<s.length(); i++){ 
			if(String.valueOf(s.charAt(i)).isEmpty() || Character.isWhitespace(s.charAt(i))){
				str += "%20";  
			}else{
				str +=String.valueOf(s.charAt(i));
			}
		}
		return str;
	}

	public static void main(String[] args) {
		System.out.println(replaceSpace("My code is good")); 

	}

}
