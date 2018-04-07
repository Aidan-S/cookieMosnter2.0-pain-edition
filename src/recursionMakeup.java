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

	    
	    longestPath(l2, w2, array, array[l2][w2], stack, 0);
	    
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
	
	
	
	
	

	private static Stack<Integer> longestPath(int l, int w, int[][] array, int num, Stack<Integer> stack, int c) {
		int[] direction = { 0, 0, 0, 0, 0, 0, 0, 0};
		int tempC = c;
		
		//top left
		if(l-1 >= 0 && w-1 >= 0 && array[l-1][w-1] == num){
			tempC++;
			direction[0] = longestPath( l-1, w-1, array, num, stack, tempC).size();
		}
		//top mid
		if(l-1 >= 0 && array[l-1][w] == num){
			tempC++;
			direction[1] = longestPath( l-1, w, array, num, stack, tempC).size();	
		}
		//top right
		if(l-1 >= 0 && w+1 < array[0].length && array[l-1][w+1] == num){
			tempC++;
			direction[2] = longestPath( l-1, w+1, array, num, stack, tempC).size();		
		}		
		//mid left
		if(w-1 >= 0 && array[l][w-1] == num){
			tempC++;
			direction[3] = longestPath( l, w-1, array, num, stack, tempC).size();		
		}
		//mid right
		if(w+1 < array[0].length && array[l][w+1] == num){
			tempC++;
			direction[4] = longestPath( l, w+1, array, num, stack, tempC).size();	
		}
		//bot left
		if(l+1 < array.length && w-1 >= 0 && array[l+1][w-1] == num){
			tempC++;
			direction[5] = longestPath( l+1, w-1, array, num, stack, tempC).size();		
		}
		//bot mid
		if(l+1 < array.length && array[l+1][w] == num){
			tempC++;
			direction[6] = longestPath( l+1, w, array, num, stack, tempC).size();		
		}
		//bot right
		if(l+1 < array.length && w+1 < array[0].length && array[l+1][w+1] == num){
			tempC++;
			direction[7] = longestPath( l+1, w+1, array, num, stack, tempC).size();		
		}
		int max = 0;
		int index = -1;
		for(int i = 0; i < 8; i++) {
			if(direction[i] > max) {
				max = direction[i];
				index = i;
			}
		}
		
		switch (index) {
		case 0:  tempC++;
				 return longestPath( l-1, w-1, array, num, stack, tempC);
				 
		case 1:  tempC++;
		 		 return longestPath( l-1, w, array, num, stack, tempC);
                 
        case 2:  tempC++;
				 return longestPath( l-1, w+1, array, num, stack, tempC);
                 
        case 3:  tempC++;
				 return longestPath( l, w-1, array, num, stack, tempC);
                 
        case 4:  tempC++;
				 return longestPath( l, w+1, array, num, stack, tempC);
                 
        case 5:  tempC++;
				 return longestPath( l+1, w-1, array, num, stack, tempC);
                 
        case 6:  tempC++;
				 return longestPath( l+1, w, array, num, stack, tempC);
                 
        case 7:  tempC++;
				 return longestPath( l+1, w+1, array, num, stack, tempC);
                 
        default:  return stack;
                 
        
		
		
		}	

	}
	

}
