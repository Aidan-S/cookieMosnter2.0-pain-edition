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
		return 3 + (int)(Math.random() * 4);
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
	    //ask for array dimensions
	    System.out.print("Length: ");
	    int l = kb.nextInt();
	    System.out.print("Width: ");
	    int w = kb.nextInt();
	    
	    //make and fill up the array
	    int[][] array = new int[l][w];
	    for(int r = 0; r < l; r++) {
	    	for(int c = 0; c < w; c++) {
	    		array[r][c] = rand();	
		    }	
	    }
	    display(array);
	    
	    //generate a random point
	    int l2 = (int)(Math.random() * l);
	    int w2 = (int)(Math.random() * w);
	    
	    //make 2 stacks that will contain the points and enter in the first point
	    Stack<Integer> stackEasy = new Stack<Integer>();
	    Stack<Integer> stackHard = new Stack<Integer>();
	    stackEasy.push(w2);
	    stackEasy.push(l2); 
	    stackHard.push(w2);
	    stackHard.push(l2);
	    
	    //tests the longestPathEasy method
	    stackEasy = longestPathEasy(l2, w2, array, array[l2][w2], stackEasy);
	    //iterates through the stack, printing out its contents in a more readable format
	    String str = "";
	    int size = stackEasy.size()/2;
	    for(int i = 0; i < size; i++) {
	    	str = "[" + stackEasy.pop() + ", " + stackEasy.pop() + "], " + str;
	    }
	    System.out.println("Path going right and down:");
	    System.out.println(str.substring(0, str.length() - 2) + " - visits " + size +" points");
	    
	   
	    //tests the longestPath method 
	    stackHard = longestPath(l2, w2, array, array[l2][w2], stackHard, 0);
	    //iterates through the stack, printing out its contents in a more readable format
	    String str2 = "";
	    int size2 = stackHard.size()/2;
	    for(int i = 0; i < size2; i++) {
	    	str2 = "[" + stackHard.pop() + ", " + stackHard.pop() + "], " + str2;
	    }
	    System.out.println("Path going in all directions:"); 
	    System.out.println(str2.substring(0, str2.length() - 2) + " - visits " + size2 +" points");
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
		//boolean variables to represent whether the path can go right or if it can go down
		boolean r = ( w+1 < array[0].length && array[l][w+1] == num );
		boolean d = ( l+1 < array.length && array[l+1][w] == num );
		
		Stack<Integer> newStack = (Stack<Integer>) stack.clone(); 
		
		//check if the path can only go down
		if(d && !r) {
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w);
			newStack.push(l+1);
			return longestPathEasy(l+1, w, array, num, newStack);
		}else{
			//check if the path can only go right
			if(r && !d) {
				newStack = (Stack<Integer>) stack.clone();
				newStack.push(w+1);
				newStack.push(l);
				return longestPathEasy(l, w+1, array, num, newStack);
			}else{
				//check if the path can only go both directions
				if(d && r) {
					Stack<Integer> pathDown = longestPathEasy(l+1, w, array, num, newStack);
					Stack<Integer> pathRight = longestPathEasy(l, w+1, array, num, newStack);
					
					//I have to call the longestPathEasy method again, instead of just returning pathDown or pathRight; those were called without adding the new points
					if(pathDown.size() > pathRight.size()) {
						newStack = (Stack<Integer>) stack.clone();
						newStack.push(w);
						newStack.push(l+1);
						return longestPathEasy(l+1, w, array, num, newStack);
					}else{
						newStack = (Stack<Integer>) stack.clone();
						newStack.push(w+1);
						newStack.push(l);
						return longestPathEasy(l, w+1, array, num, newStack);
					}
				}else{
					//return the original stack if it can't go any farther
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
		//array of 
		int[] direction = { 0, 0, 0, 0, 0, 0, 0, 0};
		
		//counter for the length of the path without changing c
		int tempC = c;
		
		//copy stack in order to prevent changing stack
		Stack<Integer> newStack = (Stack<Integer>) stack.clone();
		
		
		//check the top left, add the length of the path in that direction to the array
		if(l-1 >= 0 && w-1 >= 0 && array[l-1][w-1] == num && !visited(newStack, l-1, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l-1);
			direction[0] = longestPath( l-1, w-1, array, num, newStack, tempC).size();
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the top mid, add the length of the path in that direction to the array
		if(l-1 >= 0 && array[l-1][w] == num && !visited(newStack, l-1, w)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w);
			newStack.push(l-1);
			direction[1] = longestPath( l-1, w, array, num, newStack, tempC).size();	
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the top right, add the length of the path in that direction to the array
		if(l-1 >= 0 && w+1 < array[0].length && array[l-1][w+1] == num && !visited(newStack, l-1, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l-1);
			direction[2] = longestPath( l-1, w+1, array, num, newStack, tempC).size();		
		}	
		newStack = (Stack<Integer>) stack.clone();
		
		//check the mid left, add the length of the path in that direction to the arraymid left
		if(w-1 >= 0 && array[l][w-1] == num && !visited(newStack, l, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l);
			direction[3] = longestPath( l, w-1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the mid right, add the length of the path in that direction to the array
		if(w+1 < array[0].length && array[l][w+1] == num && !visited(newStack, l, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l);
			direction[4] = longestPath( l, w+1, array, num, newStack, tempC).size();	
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the bot left, add the length of the path in that direction to the array
		if(l+1 < array.length && w-1 >= 0 && array[l+1][w-1] == num && !visited(newStack, l+1, w-1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w-1);
			newStack.push(l+1);
			direction[5] = longestPath( l+1, w-1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the bot mid, add the length of the path in that direction to the array
		if(l+1 < array.length && array[l+1][w] == num && !visited(newStack, l+1, w)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w);
			newStack.push(l+1);
			direction[6] = longestPath( l+1, w, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//check the bot right, add the length of the path in that direction to the array
		if(l+1 < array.length && w+1 < array[0].length && array[l+1][w+1] == num && !visited(newStack, l+1, w+1)){
			tempC++;
			newStack = (Stack<Integer>) stack.clone();
			newStack.push(w+1);
			newStack.push(l+1);
			direction[7] = longestPath( l+1, w+1, array, num, newStack, tempC).size();		
		}
		newStack = (Stack<Integer>) stack.clone();
		
		//determine which possible path is the longest
		int max = 0;
		int index = -1;
		for(int i = 0; i < 8; i++) {
			if(direction[i] > max) {
				max = direction[i];
				index = i;
			}
		}
		
		//after determining the longest path, commit that path and then pursue it
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
		
	    //pop off 2 at a time and compare them to the given l and w
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
