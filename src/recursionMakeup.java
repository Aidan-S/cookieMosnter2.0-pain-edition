import java.util.Scanner;
import java.util.Stack;

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
	    
	    int l2 = (int)(Math.random() * l);
	    int w2 = (int)(Math.random() * w);
	    
	    Stack<Integer> stack = new Stack<Integer>();
	    
	    display(array);
	    System.out.println(l2);
	    System.out.println(w2);
	    System.out.println(array[l2][w2] + "\n");
	    
	    System.out.println(longestPathEasy(l2, w2, array, array[l2][w2], stack));
	    
	    //System.out.println(longestPath(l2, w2, array, array[l2][w2], 0, 0));
	    
	}

	
	private static Stack<Integer> longestPathEasy(int l, int w, int[][] array, int num, Stack<Integer> stack) {
		//down
		if(l > array[0].length && array[1+1][w] == num && array[1][w+1] != num) {
			stack.push(l+1);
			stack.push(w);
			return longestPathEasy(l+1, w, array, num, stack);
		}
		//right
		if(w > array.length && array[1][w+1] == num && array[1+1][w] != num) {
			stack.push(l);
			stack.push(w+1);
			return longestPathEasy(l, w+1, array, num, stack);
		}
		//both
		if(array[1][w+1] == num && array[1+1][w] == num) {
			//if down stack bigger add and return down stack
			if(longestPathEasy(l+1, w, array, num, stack).size() > longestPathEasy(l, w+1, array, num, stack).size()) {
				stack.push(l+1);
				stack.push(w);
				return longestPathEasy(l+1, w, array, num, stack);
			//if right stack bigger add and return right stack
			}else{
				stack.push(l);
				stack.push(w+1);
				return longestPathEasy(l, w+1, array, num, stack);
			}
			
		}
		//neither
		return stack;
	}
	
	
	
	
	

//	private static Stack<Integer> longestPath(int l, int w, int[][] array, int num, Stack<Integer> stack) {
//		
//		if(array[1][w] != num) {
//			return null;
//		}
//		
//		
//		
//		//System.out.println("[" + l + "] [" + w + "]");
//		
//		boolean r = false;
//		boolean d = false;
//
//		//right
//		if(w <= array[0].length && array[l][w+1] == num) {
//			r = true;
//		}
//		//down
//		if(l <= array.length || array[l+1][w] == num) {
//			d = true;
//		}
//		
//		if(r&&d) {
//			if(longestPathEasy(l+1, w, array, num, stack) != null) {
//				
//			}
//			if(longestPathEasy(l, w+1, array, num, stack) != null) {
//				
//			}
//		}else { 
//			if(r){
//				//longpath1
//			}
//			if(d){
//				//longpath2
//			}
//			System.out.println(c);
//			
//		}
//
//		
//		
//		
//		return 0;
//	}


	

}
