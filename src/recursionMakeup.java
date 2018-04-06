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
	    
	    //int l2 = (int)(Math.random() * l);
	    //int w2 = (int)(Math.random() * w);
	    
	    Stack<Integer> stack = new Stack<Integer>();
	    
	    display(array);
	    
	    //System.out.println(l2);
	    //System.out.println(w2);
	    
	    System.out.print("Length: ");
	    int l2 = kb.nextInt();
	    System.out.print("Width: ");
	    int w2 = kb.nextInt();
	    
	    System.out.println(array[l2][w2] + "\n");
	    
	    
	    stack = longestPathEasy(l2, w2, array, array[l2][w2], stack);
	    
	    int s = stack.size()/2;
	    
	    String str = "";
	    for(int i = 0; i < s; i++) {
	    	str = "[" + stack.pop() + ", " + stack.pop() + "]  " + str;
	    }
	    System.out.println("[" + l2 + ", " + w2 + "]  " + str);
	    
	    //System.out.println(longestPathEasy(l2, w2, array, array[l2][w2], stack));
	    
	    //System.out.println(longestPath(l2, w2, array, array[l2][w2], 0, 0));
	    
	}

	
	private static Stack<Integer> longestPathEasy(int l, int w, int[][] array, int num, Stack<Integer> stack) {
		boolean r = ( w+1 < array[0].length && array[l][w+1] == num );
		boolean d = ( l+1 < array.length && array[l+1][w] == num );
		Stack<Integer> newStack = new Stack<Integer>(); 
		
		//down
		if(d && !r) {
			newStack = stack;
			newStack.push(w);
			newStack.push(l+1);
			return longestPathEasy(l+1, w, array, num, newStack);
		}else{
			//right
			if(r && !d) {
				newStack = stack;
				newStack.push(w+1);
				newStack.push(l);
				return longestPathEasy(l, w+1, array, num, newStack);
			}else{
				//both
				if(d && r) {
					if(longestPathEasy(l+1, w, array, num, newStack).size() > longestPathEasy(l, w+1, array, num, newStack).size()) {
						newStack = stack;
						newStack.push(w);
						newStack.push(l+1);
						return longestPathEasy(l+1, w, array, num, newStack);
					}else{
						newStack = stack;
						newStack.push(w+1);
						newStack.push(l);
						return longestPathEasy(l, w+1, array, num, newStack);
					}
				}else{
					//neither
					return stack;
				}		
			}
		}	
	}
	
	
	
	
	

	private static Stack<Integer> longestPath(int l, int w, int[][] array, int num, Stack<Integer> stack) {
		
		
		return null;
	}


	

}
