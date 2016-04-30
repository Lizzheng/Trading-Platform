
public class CountingSheep {
	
public static void countSheep(int N){
		
		boolean hasAllNumbers = false;
		char[] countingNumbers = {'0','1','2','3','4','5','6','7','8','9'}; 
		
		if(N == 0){
			System.out.println("INSOMNIA");
		}else{
			int multiplier = 1;
			while(!hasAllNumbers){ 
				int product;
				product = multiplier*N; 
				String sProduct = String.valueOf(product); 
				char[] productNums = sProduct.toCharArray(); 
				
				for(int i=0; i<productNums.length; i++){
					for(int j=0; j<countingNumbers.length; j++){
						if(productNums[i] == countingNumbers[j]){
							countingNumbers[j] = 'x'; 
						}
					}
				}
				
				if(checkNumbers(countingNumbers)){
					hasAllNumbers = true;
					System.out.println(product);
				}else{
					multiplier++; 
				}
			}
		}		
	}
	
	public static boolean checkNumbers(char[] list){
		for(int i=0; i<list.length; i++){
			if(list[i] != 'x'){
				return false;
			}
		}
		return true;
	}
	

	public static void main(String[] args) {
		countSheep(1692); 
	}

}
