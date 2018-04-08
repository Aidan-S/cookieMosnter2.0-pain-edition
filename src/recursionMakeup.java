import java.util.Scanner;
import java.util.Stack;

public class recursionMakeup {

	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: return a random int 3-6
	 * @return: the random number
	 */
	private static int rand() {
		return 3 + (int)(Math.random() * ((6 - 3) + 1));
	}
	
	
	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: print out a 2D array to the console 
	 * @param array: array to be printed 
	 * @return: none
	 */
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
	
	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: main method to test the longest path methods
	 * @param args: string array of arguments 
	 * @return: none
	 */
	public static void main(String[] args) {
	    Scanner kb = new Scanner(System.in);
//	    System.out.print("Length: ");
//	    int l = kb.nextInt();
//	    System.out.print("Width: ");
//	    int w = kb.nextInt();
//	    
//	    
//	    int[][] array = new int[l][w];
//	    
//	    for(int r = 0; r < l; r++) {
//	    	for(int c = 0; c < w; c++) {
//	    		array[r][c] = rand();	
//		    }	
//	    }
//	    
//	    display(array);
	    
	    
	    
	    
	    
	    int[][] array = {{0,0,0,1},
	    				 {0,0,0,1},
	    				 {1,1,1,1},
	    				 {0,0,0,1},
	    				 {0,0,0,1},
	    				 {0,0,0,1}};
	    
	    //int l2 = (int)(Math.random() * l);
	    //int w2 = (int)(Math.random() * w);
	    
	    Stack<Integer> stack = new Stack<Integer>();
	    
	    //Stack<Integer> visited = new Stack<Integer>();
	    
	    //System.out.println(l2);
	    //System.out.println(w2);
	    
	    System.out.print("Length: ");
	    int l2 = kb.nextInt();
	    System.out.print("Width: ");
	    int w2 = kb.nextInt();
	    
	    System.out.println(array[l2][w2] + "\n");
	    display(array);
	
	    stack.push(w2);
	    stack.push(l2);
	    
	    
//	    stack = longestPathEasy(l2, w2, array, array[l2][w2], stack);
//	    
//	    int s = stack.size()/2;
//	    
//	    String str = "";
//	    for(int i = 0; i < s; i++) {
//	    	str = "[" + stack.pop() + ", " + stack.pop() + "]  " + str;
//	    }
//	    System.out.println("[" + l2 + ", " + w2 + "]  " + str);
	    
	    //System.out.println(longestPathEasy(l2, w2, array, array[l2][w2], stack));

	    
	    stack = longestPath(l2, w2, array, array[l2][w2], stack, 0);
	    
	    int s = stack.size()/2;
	    
	    String str = "";
	    for(int i = 0; i < s; i++) {
	    	str = "[" + stack.pop() + ", " + stack.pop() + "]  " + str;
	    }
	    
	    System.out.println(str);
	    
	    
	}

	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: determine the longest path by moving to the left or down
	 * @param l: which row the point is in the array  
	 * @param w: which column the point is in the array
	 * @param array: the array being evaluated
	 * @param num: what number the path needs to be
	 * @param stack: stack containing the points visited
	 * @return: stack that contains all the points visited
	 */
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
					Stack<Integer> pathDown =longestPathEasy(l+1, w, array, num, newStack);
					Stack<Integer> pathRight = longestPathEasy(l, w+1, array, num, newStack);
					if(pathDown.size() > pathRight.size()) {
						newStack = stack;
						newStack.push(w);
						newStack.push(l+1);
						return pathDown;
					}else{
						newStack = stack;
						newStack.push(w+1);
						newStack.push(l);
						return pathRight;
					}
				}else{
					//neither
					return stack;
				}		
			}
		}	
	}
	
	
	
	
	
	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: determine the longest path by moving to the left or down
	 * @param l: which row the point is in the array  
	 * @param w: which column the point is in the array
	 * @param array: the array being evaluated
	 * @param num: what number the path needs to be
	 * @param stack: stack containing the points visited
	 * @param c: int to count how long the other paths are
	 * @return: stack that contains all the points visited
	 */
	private static Stack<Integer> longestPath(int l, int w, int[][] array, int num, Stack<Integer> stack, int c) {
		int[] direction = { 0, 0, 0, 0, 0, 0, 0, 0};
		int tempC = c;
		
		Stack<Integer> newStack = (Stack<Integer>) stack.clone();
		
		
		//top left
		if(l-1 >= 0 && w-1 >= 0 && array[l-1][w-1] == num && !visited(newStack, l-1, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l-1);
			direction[0] = longestPath( l-1, w-1, array, num, newStack, tempC).size();
		}
		newStack = (Stack<Integer>) stack.clone();
		//top mid
		if(l-1 >= 0 && array[l-1][w] == num && !visited(newStack, l-1, w)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w);
			newStack.push(l-1);
			direction[1] = longestPath( l-1, w, array, num, newStack, tempC).size();	
		}
		newStack = (Stack<Integer>) stack.clone();
		//top right
		if(l-1 >= 0 && w+1 < array[0].length && array[l-1][w+1] == num && !visited(newStack, l-1, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l-1);
			direction[2] = longestPath( l-1, w+1, array, num, newStack, tempC).size();		
		}	
		newStack = (Stack<Integer>) stack.clone();
		//mid left
		if(w-1 >= 0 && array[l][w-1] == num && !visited(newStack, l, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l);
			direction[3] = longestPath( l, w-1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		//mid right
		if(w+1 < array[0].length && array[l][w+1] == num && !visited(newStack, l, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l);
			direction[4] = longestPath( l, w+1, array, num, newStack, tempC).size();	
		}
		newStack = (Stack<Integer>) stack.clone();
		//bot left
		if(l+1 < array.length && w-1 >= 0 && array[l+1][w-1] == num && !visited(newStack, l+1, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l+1);
			direction[5] = longestPath( l+1, w-1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		//bot mid
		if(l+1 < array.length && array[l+1][w] == num && !visited(newStack, l+1, w)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w);
			newStack.push(l+1);
			direction[6] = longestPath( l+1, w, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		//bot right
		if(l+1 < array.length && w+1 < array[0].length && array[l+1][w+1] == num && !visited(newStack, l+1, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l+1);
			direction[7] = longestPath( l+1, w+1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		
		int max = 0;
		int index = -1;
		for(int i = 0; i < 8; i++) {
			if(direction[i] > max) {
				max = direction[i];
				index = i;
			}
		}
		
		//after determining the longest path, commit that path and return it
		switch (index) {
		case 0:  c++;
				 stack.push(w-1);
				 stack.push(l-1);
				 return longestPath( l-1, w-1, array, num, stack, c);
				 
		case 1:  c++;
				stack.push(w);
				 stack.push(l-1);
		 		 return longestPath( l-1, w, array, num, stack, c);
                 
        case 2:  c++;
        		 stack.push(w+1);
		 		 stack.push(l-1);
				 return longestPath( l-1, w+1, array, num, stack, c);
                 
        case 3:  c++;
        		 stack.push(w-1);
		 		 stack.push(l);
				 return longestPath( l, w-1, array, num, stack, c);
                 
        case 4:  c++;
        		 stack.push(w+1);
        		 stack.push(l);
				 return longestPath( l, w+1, array, num, stack, c);
                 
        case 5:  c++;
        		 stack.push(w-1);
        		 stack.push(l+1);
				 return longestPath( l+1, w-1, array, num, stack, c);
                 
        case 6:  c++;
        		 stack.push(w);
		 		 stack.push(l+1);
				 return longestPath( l+1, w, array, num, stack, c);
                 
        case 7:  c++;
        		 stack.push(w+1);
        		 stack.push(l+1);
				 return longestPath( l+1, w+1, array, num, stack, c);
                 
        default:  return stack;
                 
        
		
		
		}	

	}
	
	/**
	 * @author Aidan-S
	 * date: March 9th, 2018
	 * method: check if the point is in the stack of visited points
	 * @param stack: stack of points to compare to the given row and column coordinates
	 * @param l: the row coordinate 
	 * @param w: the column coordinate
	 * @return: none
	 */
	private static boolean visited(Stack<Integer> stack, int l, int w) {
		int s = stack.size()/2;
	    int l2;
	    int w2;
		
	    for(int i = 0; i < s; i++) {
	    	l2 = stack.pop();
	    	w2 = stack.pop();
	    	if(l == l2 && w == w2) {
	    		return true;
	    	}
	    }
			
		return false;
	}
	
	

}
