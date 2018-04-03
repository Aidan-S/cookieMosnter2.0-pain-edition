import java.util.Scanner;

public class recursionMakeup {

	
	private static int rand() {
		return 3 + (int)(Math.random() * ((6 - 3) + 1));
	}
	
	
	public static void main(String[] args) {
	    Scanner kb = new Scanner(System.in);
	    System.out.print("Width: ");
	    int l = kb.nextInt();
	    System.out.print("Length: ");
	    int w = kb.nextInt();
	    
	    int[][] array = new int[l][w];
	    
	    for(int r = 0; r < l; r++) {
	    	for(int c = 0; c < l; c++) {
	    		array[r][c] = rand();	
		    }	
	    }
	    
	    
	    
	    
	    
	    
	    
	}

}
