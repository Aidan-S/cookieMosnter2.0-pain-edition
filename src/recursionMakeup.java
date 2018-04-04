import java.util.Scanner;

public class recursionMakeup {

	
	private static int rand() {
		return 3 + (int)(Math.random() * ((6 - 3) + 1));
	}
	
	
	private static void display(int[][] array) {
		String line = "";
		for(int r = 0; r < array.length; r++) {
	    	for(int c = 0; c < array[0].length; c++) {
	    		line += array[r][c] + ", ";	
		    }	
	    	line += "\n";
	    }
		System.out.println(line);
	}
	
	
	public static void main(String[] args) {
	    Scanner kb = new Scanner(System.in);
	    System.out.print("Length: ");
	    int l = kb.nextInt();
	    System.out.print("Width: ");
	    int w = kb.nextInt();
	    
	    
	    int[][] array = new int[l][w];
	    
	    for(int r = 0; r < l; r++) {
	    	for(int c = 0; c < w; c++) {
	    		array[r][c] = rand();	
		    }	
	    }
	    
	    int l2 = (int)(Math.random() * ((l)));
	    int w2 = (int)(Math.random() * ((w)));
	    
	    display(array);
	    System.out.println(l2);
	    System.out.println(w2);
	    
	    
	    System.out.println(longestPathEasy(l2, w2, array, array[l2][w2], 0));
	    
	    //System.out.println(longestPath(l2, w2, array, array[l2][w2], 0, 0));
	    
	}


	private static int longestPathEasy(int l, int w, int[][] array, int num, int c) {
		
		if(array[1][w] != num) {
			return -1;
		}
		c++;
		
		
		System.out.println("[" + l + "] [" + w + "]");
		
		boolean r = false;
		boolean d = false;

		//right
		if(w <= array[0].length && array[l][w+1] != -1) {
			r = true;
		}
		//down
		if(l <= array.length || array[l+1][w] != -1) {
			d = true;
		}
		
		if(r&&d) {
			//longpath1
			//longpath2
		}else { 
			if(r){
				//longpath1
			}
			if(d){
				//longpath2
			}
			System.out.println(c);
			
		}

		
		
		
		return 0;
	}


	private static int longestPath(int l, int w, int[][] array, int num, int last, int c) {
		
		if(array[1][w] != num) {
			return -1;
		}
		c++;
		//make sure we dont go same path
		
		
		
		//up
		if(l != 0 || array[l][w] != -1) {
			
		}
		//left
		if(array[l][w] != -1) {
			
		}
		//right
		if(array[l][w] != -1) {
			
		}
		//down
		if(array[l][w] != -1) {
			
		}
		
		
		
		return 0;
	}

}
