import java.util.*;
import java.io.*;

/* RUN Syntax.java */
/* Author: Vergel Joseph G. Monterozo */

class Syntax{
	String data_types []	= {"int", "float", "double", "char"};
	String upperAlpha []	= {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
	String lowerAlpha []	= {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	String varnum	  []	= {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

	List data_List 			= Arrays.asList(data_types);
	List lowerAlpha_List 	= Arrays.asList(lowerAlpha);
	List upperAlpha_List 	= Arrays.asList(upperAlpha);
	List varnum_list 		= Arrays.asList(varnum);

	public static void main (String args[]) throws Exception{
		Syntax 					s = new Syntax();
		Queue<String> 		queue = new LinkedList<String>();
		Stack<Character>	stack = new Stack<Character>();
		int 				f_flag = 0;
		String 				text 	= "";

		System.out.print("Enter input file name >> ");
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        String input = bufferRead.readLine();

        System.out.print("Enter output file name >> ");
		BufferedReader bufferRead2 = new BufferedReader(new InputStreamReader(System.in));
        String output = bufferRead2.readLine();

		/* File read and File Write */ 
		FileInputStream 	fs 		= new FileInputStream(input);
		BufferedReader		br 		= new BufferedReader(new InputStreamReader(fs));
		File 				file 	= new File(output);
	  	file.createNewFile();
	    FileWriter 		fileWriter 	= new FileWriter(file, true);
	    /* End of file declaration */

	    String str;
	    String temp = "";
	    int cases = 0;
	    while ((str = br.readLine()) != null){
	    		String [] 	split 		= str.split("[\\s]");
	    		List 		split_list	= Arrays.asList(split);
	    		String		st			= "";

	    		for (int i = 0; i < split.length; i++){
	    			if(split[i].length() > 0){	
	    			// Thread.sleep(2000);
	    			// System.out.println("> " + split[i]);
	    			if (split[i] == ","){
	    				if (split[i+1] != ";"){
	    					queue.add (split[i]);
	    				}else{
	    					if (stack.isEmpty()){
		    					text = s.check(queue, f_flag, stack);
		    					fileWriter.write(text + "\n");
		    					f_flag = 0;
		    					cases ++;
		    					queue.add (split[i]);
		    				}else{
		    					queue.add (split[i]);
		    				}
	    				}
	    			}else if (split[i].equals(";")){
	    				if (!stack.isEmpty()){
	    					if (stack.peek() == '(' || stack.peek() == '['){
	    						f_flag = 1;
	    					}
	    				}else{
		    				if (queue.isEmpty()){
		    					queue.add (split[i]);
		    				}else{
		    					if (stack.isEmpty()){
			    					text = s.check(queue, f_flag, stack);
			    					fileWriter.write(text + "\n");
			    					f_flag = 0;
			    					cases ++;
			    					queue.add (split[i]);
			    				}else{
			    					queue.add (split[i]);
			    				}
		    				}
		    			}
	    			}else if (s.data_List.contains(split[i]) || split[i] == "return" || split[i] == "void"){
	    				if (queue.isEmpty()){
	    					queue.add (split[i]);
	    				}else{
	    					// System.out.println(temp);
	    					// System.out.println(temp.charAt(temp.length()-1));
	    					if (temp.charAt(temp.length()-1) != ';' && temp.charAt(temp.length()-1) != '}'){
		    					queue.add (split[i]);
		    				}else{
		    					if (stack.isEmpty()){
			    					 	
		    				}
	    				}
	    			}else{
	    				for (int j = 0; j < split[i].length(); j++){
	    					// System.out.println("FLAG: " + f_flag);	
	    					// System.out.println("SPLIT: " + split[i].charAt(j));	
	    					if (split[i].charAt(j) == ','){
			    				if (split[i+1] != ";"){
			    					if (st.length()>0){
			    						queue.add (st);
			    					}
			    					queue.add (Character.toString(split[i].charAt(j)));
			    					st = "";
			    				}else{	
			    					if (st.length()>0){		    				
			    						queue.add (st);
			    					}
			    					queue.add (Character.toString(split[i].charAt(j)));
			    					text = s.check(queue, f_flag, stack);
			    					fileWriter.write(text + "\n");
			    					f_flag = 0;
			    					cases ++;
			    					st = "";
			    				}
			    			}else if (split[i].charAt(j) == ';'){
			    				if (!stack.isEmpty()){
			    					if (stack.peek() == '(' || stack.peek() == '[' || stack.peek() == '\''){
			    						f_flag = 1;
			    					}
			    				}else{
				    				if (queue.isEmpty()){
				    					if (st.length()>0){		    				
				    						queue.add (st);
				    					}
				    					queue.add (Character.toString(split[i].charAt(j)));
				    					st = "";
				    				}else{
				    					if (st.length()>0){		    				
				    							queue.add (st);
				    					}
				    					queue.add (Character.toString(split[i].charAt(j)));		
				    					text = s.check(queue, f_flag, stack);
				    					fileWriter.write(text + "\n");
				    					f_flag = 0;
				    					cases ++;
				    					st = "";
					    			}
				    			}
			    			}else{
			    				if (split[i].charAt(j) == '(' || split[i].charAt(j) == ')' || split[i].charAt(j) == '[' || split[i].charAt(j) == ']' || split[i].charAt(j) == '{' || split[i].charAt(j) == '}' || split[i].charAt(j) == ' ' || split[i].charAt(j) == ';' || split[i].charAt(j) == ',' || split[i].charAt(j) == '\'' || split[i].charAt(j) == '='){
				    				// System.out.println(split[i].charAt(j));
				    				if (st.length()> 0){
				    					queue.add(st);
				    					st = "";
				    				}
				    				queue.add (Character.toString(split[i].charAt(j)));
				    				// if (split[i].charAt(j) == '(' || split[i].charAt(j) == ')' || split[i].charAt(j) == '[' || split[i].charAt(j) == ']' || split[i].charAt(j) == '{' || split[i].charAt(j) == '}' || split[i].charAt(j) == '\''){
			    					// 	stack.push (split[i].charAt(j));
			    					// }
			    					if (stack.isEmpty()){
				    					if (split[i].charAt(j) == '(' || split[i].charAt(j) == '[' || split[i].charAt(j) == '{' || split[i].charAt(j) == '\''){
					    					stack.push (split[i].charAt(j));
				    					}
				    				}else{
				    					if (stack.peek() == '(' || stack.peek() == '[' || stack.peek() == '{' || stack.peek() == '\''){
				    						if (split[i].charAt(j) == stack.peek() && split[i].charAt(j) != '\''){
				    							stack.push (split[i].charAt(j));
				    							// queue.add (Character.toString(split[i].charAt(j)));
					    						st = "";
				    						}else if (split[i].charAt(j) == ')'){
				    							if (stack.peek() == '('){
				    								stack.pop();
				    							}else{
				    								f_flag = 1;
				    							}
				    						}else if (split[i].charAt(j) == '}'){
				    							if (stack.peek() == '{'){
				    								stack.pop();
				    								// queue.add (Character.toString(split[i].charAt(j)));
					    							st = "";
				    							}else{
				    								f_flag = 1;
				    							}
				    						}else if (split[i].charAt(j) == ']'){
				    							if (stack.peek() == '['){
				    								stack.pop();
				    							}else{
				    								f_flag = 1;
				    							}
				    						}else if (split[i].charAt(j) == '\''){
				    							if (stack.peek() == '\''){
				    								stack.pop();
				    								// queue.add (Character.toString(split[i].charAt(j)));
					    							st = "";
				    							}else{
				    								f_flag = 1;
				    							}
				    						}else{
				    							if (stack.peek() == '('){
				    								// System.out.println("HERE");
				    								if (split[i].charAt(j) == ',' || split[i].charAt(j) == '[' || split[i].charAt(j) == ']'){
				    									// System.out.println("BES");
					    								st = "";
					    								f_flag = 0;
				    								}else{
				    									// System.out.println("RLY 1");
				    									f_flag = 1;
				    								}
				    							}else if (stack.peek() == '{'){
				    								// System.out.println("HERE");
				    								if (split[i].charAt(j) == ',' || split[i].charAt(j) == '[' || split[i].charAt(j) == ']' || split[i].charAt(j) == ';' || split[i].charAt(j) == '='){
				    									// System.out.println("BES");
					    								st = "";
					    								f_flag = 0;
				    								}else{
				    									// System.out.println("RLY 1");
				    									f_flag = 1;
				    								}
				    							}else{
				    								// System.out.println("RLY 2");
				    								f_flag = 1;
				    							}
				    						}
				    					}	
				    				}
			    				}else{
			    					st = st + split[i].charAt(j);
				    				if (s.data_List.contains(st) ||  st.equals("void") || st.equals("return")){
				    					queue.add (st);
				    					st = "";
				    				}
			    				}
			    			}
	    				}

	    				if (st.length() > 0){
			    			queue.add(st);
			    			st = "";
			    		}
	    			}
	    			temp = split[i];
	    		}
	    	}
	    }

	    if (!queue.isEmpty()){
	    	cases++;
	    	text = s.check(queue, f_flag, stack);
	    	fileWriter.write(text + "\n");
	    	f_flag = 0;
	    }

	    System.out.println(cases + " test case(s) found");
	    fileWriter.close(); 
	    /* PRINT QUEUE SIZE */
	    // System.out.println("Queue size: " + queue.size());
	    // System.out.println();

	    /* PRINT ELEMENTS OF QUEUE */
	    // while (!stack.isEmpty()){
	    // 	System.out.println(stack.peek());
	    // 	stack.pop();
	    // }
	}

	public String check (Queue<String> queue, int f_flag, Stack<Character> stack){
		List l = (List) queue;
		Syntax s = new Syntax();
		Queue<Boolean> state = new LinkedList<Boolean>();
		Queue<String> cpy = new LinkedList<String>(queue);

		if ((!l.contains("=") && l.contains(";") && !l.contains("(")) || (l.contains("=") && !l.contains(";") && !l.contains("(")) || (l.contains("=") && l.contains(";") && !l.contains("(")) || (!l.contains("=") && !l.contains(";") && !l.contains("(") && (l.contains("int") || l.contains("float") || l.contains("double") || l.contains("char")))){
			int row = 0, col = 0;
		    String x = "", y = "";

		    while (queue.size() >= 1){
		    	for (int m = 0; m < 1; m++){
		    		if (!queue.isEmpty()){
		    			if (queue.element().length() > 1){
			    			x = (String)queue.element();
					    	row = s.getState(x, y);
					    	queue.remove();
			    		}else{
			    			x = queue.element();
			    			row = s.getState(x, y);
					    	queue.remove();
			    		}


			    		if (y.length() >= 1){
			    			if (getStateStatus(col,row))
				    			state.add(true);
				    		else
				    			state.add(false);
			    		}
			    		
			    	}
			    	if (!queue.isEmpty()){
			    		for (int n = 0; n < 1; n++){
			    			if (queue.element().length() > 1){
				    			y = (String)queue.element();
			    				col = s.getState (y, x);
			    				queue.remove();
				    		}else{
				    			y = queue.element();
				    			col = s.getState (y, x);
				    			queue.remove();
				    		}
			    		}
			    		
			    		if (getStateStatus(row,col))
			    			state.add(true);
			    		else
			    			state.add(false);
			    	}
			    }
		    }
		    int semc = 0;
		   	while (cpy.size() >= 1){
		   		if (cpy.size() == 1){
		   			// System.out.println(cpy.element());
		   			if (cpy.element().equals(";")){
		   				semc = 1;
		   			}
		   		}
		   		cpy.remove();
		   	}
	    	while (state.size() >= 1){
		    	if (state.element() == true){
		    		state.remove();
		    	}
		    	else{
		    		f_flag = 1;
		    		break;
		    	}
		    }
		   // System.out.println(f_flag + "   " + semc);
		    if (f_flag == 1 || semc == 0 || !stack.isEmpty()){
		    	return "INVALID VARIABLE DECLARATION";
		    }
		    else{
		    	return "VALID VARIABLE DECLARATION";
		    }
		}else if ((l.contains("(") && !l.contains(";") && !l.contains("{")) || (l.contains("(") && l.contains(";") && !l.contains("=") && !l.contains("{")) || (l.contains("(") && !l.contains(";") && !l.contains("=") && !l.contains("{"))){
			int row = 0, col = 0;
		    String x = "", y = "";

		    while (queue.size() >= 1){
		    	for (int m = 0; m < 1; m++){
		    		if (!queue.isEmpty()){
		    			if (queue.element().length() > 1){
			    			x = (String)queue.element();
					    	row = s.getState(x, y);
					    	queue.remove();
			    		}else{
			    			x = queue.element();
			    			row = s.getState(x, y);
					    	queue.remove();
			    		}


			    		if (y.length() >= 1){
			    			if (getStateStatus(col,row))
				    			state.add(true);
				    		else
				    			state.add(false);
			    		}
			    		
			    	}
			    	if (!queue.isEmpty()){
			    		for (int n = 0; n < 1; n++){
			    			if (queue.element().length() > 1){
				    			y = (String)queue.element();
			    				col = s.getState (y, x);
			    				queue.remove();
				    		}else{
				    			y = queue.element();
				    			col = s.getState (y, x);
				    			queue.remove();
				    		}
			    		}
			    		
			    		if (getStateStatus(row,col))
			    			state.add(true);
			    		else
			    			state.add(false);
			    	}
			    }
		    }
		   int semc = 0;
		   	while (cpy.size() >= 1){
		   		if (cpy.size() == 1){
		   			if (cpy.element().equals(";")){
		   				semc = 1;
		   			}
		   		}
		   		cpy.remove();
		   	}
	    	while (state.size() >= 1){
		    	if (state.element() == true){
		    		state.remove();
		    	}
		    	else{
		    		f_flag = 1;
		    		break;
		    	}
		    }
		   // System.out.println(f_flag + "   " + semc);
		    if (f_flag == 1 || semc == 0 || !stack.isEmpty()){
		    	return "INVALID FUNCTION DECLARATION";
		    }
		    else{
		    	return "VALID FUNCTION DECLARATION";
		    }
		}else{
			int row = 0, col = 0;
		    String x = "", y = "";

		    while (queue.size() >= 1){
		    	for (int m = 0; m < 1; m++){
		    		if (!queue.isEmpty()){
		    			if (queue.element().length() > 1){
			    			x = (String)queue.element();
					    	row = s.getState(x, y);
					    	queue.remove();
			    		}else{
			    			x = queue.element();
			    			row = s.getState(x, y);
					    	queue.remove();
			    		}


			    		if (y.length() >= 1){
			    			if (getStateStatus(col,row))
				    			state.add(true);
				    		else
				    			state.add(false);
			    		}
			    		
			    	}
			    	if (!queue.isEmpty()){
			    		for (int n = 0; n < 1; n++){
			    			if (queue.element().length() > 1){
				    			y = (String)queue.element();
			    				col = s.getState (y, x);
			    				queue.remove();
				    		}else{
				    			y = queue.element();
				    			col = s.getState (y, x);
				    			queue.remove();
				    		}
			    		}
			    		
			    		if (getStateStatus(row,col))
			    			state.add(true);
			    		else
			    			state.add(false);
			    	}
			    }
		    }
		    // while (!cpy.isEmpty()){
		    // 	System.out.println(cpy.element());
		    // 	cpy.remove();
		    // }
		    List qCpy = (List) cpy;
		   	int falsify = 0, flag = 0;
		   	while (cpy.size() >= 1){
		   		// System.out.println("HERE");
		   		if (s.data_List.contains(cpy.element()) && flag < 1){
		   			System.out.println(cpy.element());
		   			// System.out.println("CONTAINS DATA TYPE");
		   			if (!qCpy.contains("return")){
		   				// System.out.println("DOES NOT CONTAIN RETURN");
		   				falsify = 1;
		   				flag = 1;
		   			}else{
		   				// System.out.println("CONTAINS RETURN");
		   				falsify = 0;
		   				flag = 1;
		   			}
		   		}else if (cpy.element() == "void"){
		   			if (qCpy.contains("return")){
		   				falsify = 1;
		   				flag = 1;
		   			}else{
		   				falsify = 0;
		   				flag = 1;
		   			}
		   		}
		   		cpy.remove();
		    }
		    while (state.size() >= 1){
		    	if (state.element() == true){
		    		state.remove();
		    	}
		    	else{
		    		f_flag = 1;
		    		break;
		    	}
		    }
		   	// System.out.println(f_flag + "   " + falsify);
		    if (f_flag == 1 || falsify == 1 || !stack.isEmpty()){
		    	return "INVALID FUNCTION DEFINITION";
		    }
		    else{
		    	return "VALID FUNCTION DEFINITION";
		    }
		}
	}	

	public int getState (String current, String prev){
		// System.out.println("PREV: " + prev);
		// System.out.println("CURR: " + current);
		// System.out.println();

		if (current.length() > 1){
			if (data_List.contains(current))
				return 0;
			else if (current.equals("void"))
				return 22;
			else if (current.equals("return"))
				return 21;
			else{
				if (prev.equals("=")){
					return valueCheck(current);
				}else if (data_List.contains(prev)){
					return variableCheck(current);
				}else{
					return 23;
				}
			}
		}else{
			if (upperAlpha_List.contains(current)){
				return 1;
			}else if (lowerAlpha_List.contains(current)){
				return 2;
			}else if (varnum_list.contains(current)){
				return 3;
			}else if (current.equals(".")){
				return 4;
			}else if (current.equals("_")){
				return 5;
			}else if (current.equals(";")){
				return 6;
			}else if (current.equals("+")){
				return 7;
			}else if (current.equals("-")){
				return 8;
			}else if (current.equals("*")){
				return 9;
			}else if (current.equals("/")){
				return 10;
			}else if (current.equals("%")){
				return 11;
			}else if (current.equals("=")){
				return 12;
			}else if (current.equals("[")){
				return 13;
			}else if (current.equals("]")){
				return 14;
			}else if (current.equals("(")){
				return 15;
			}else if (current.equals(")")){
				return 16;
			}else if (current.equals("{")){
				return 17;
			}else if (current.equals("}")){
				return 18;
			}else if (current.equals("'")){
				return 19;
			}else if (current.equals(",")){
				return 20;
			}else if (current.equals("return")){
				return 21;
			}else if (current.equals("void")){
				return 22;
			}else{
				return 23;
			}
		}

	}

	public int valueCheck (String str){
		return 1;
	}

	public int variableCheck (String str){
		return 1;
	}

	public boolean getStateStatus (int row, int col){
		boolean[][] table = new boolean [24][24];

		// System.out.println("[" + row + "][" + col + "]");
		table [0][0] = false;
		table [0][1] = true;
		table [0][2] = true;
		table [0][3] = false;
		table [0][4] = false;
		table [0][5] = false;
		table [0][6] = false;
		table [0][7] = false;
		table [0][8] = false;
		table [0][9] = false;
		table [0][10] = false;
		table [0][11] = false;
		table [0][12] = false;
		table [0][13] = false;
		table [0][14] = false;
		table [0][15] = false;
		table [0][16] = false;
		table [0][17] = false;
		table [0][18] = false;
		table [0][19] = false;
		table [0][20] = false;
		table [0][21] = false;
		table [0][22] = false;
		table [0][23] = false;

		table [1][0] = false;
		table [1][1] = false;
		table [1][2] = false;
		table [1][3] = false;
		table [1][4] = false;
		table [1][5] = false;
		table [1][6] = true;
		table [1][7] = true;
		table [1][8] = true;
		table [1][9] = true;
		table [1][10] = true;
		table [1][11] = true;
		table [1][12] = true;
		table [1][13] = true;
		table [1][14] = true;
		table [1][15] = true;
		table [1][16] = true;
		table [1][17] = false;
		table [1][18] = false;
		table [1][19] = true;
		table [1][20] = true;
		table [1][21] = false;
		table [1][22] = false;
		table [1][23] = false;

		table [2][0] = false;
		table [2][1] = false;
		table [2][2] = false;
		table [2][3] = false;
		table [2][4] = false;
		table [2][5] = false;
		table [2][6] = true;
		table [2][7] = true;
		table [2][8] = true;
		table [2][9] = true;
		table [2][10] = true;
		table [2][11] = true;
		table [2][12] = true;
		table [2][13] = true;
		table [2][14] = true;
		table [2][15] = true;
		table [2][16] = true;
		table [2][17] = false;
		table [2][18] = true;
		table [2][19] = true;
		table [2][20] = true;
		table [2][21] = false;
		table [2][22] = false;
		table [2][23] = false;

		table [3][0] = false;
		table [3][1] = false;
		table [3][2] = false;
		table [3][3] = false;
		table [3][4] = true;
		table [3][5] = false;
		table [3][6] = true;
		table [3][7] = true;
		table [3][8] = true;
		table [3][9] = true;
		table [3][10] = true;
		table [3][11] = true;
		table [3][12] = false;
		table [3][13] = false;
		table [3][14] = true;
		table [3][15] = false;
		table [3][16] = false;
		table [3][17] = false;
		table [3][18] = true;
		table [3][19] = true;
		table [3][20] = true;
		table [3][21] = true;
		table [3][22] = false;
		table [3][23] = false;

		table [4][0] = false;
		table [4][1] = false;
		table [4][2] = false;
		table [4][3] = true;
		table [4][4] = false;
		table [4][5] = false;
		table [4][6] = false;
		table [4][7] = false;
		table [4][8] = false;
		table [4][9] = false;
		table [4][10] = false;
		table [4][11] = false;
		table [4][12] = false;
		table [4][13] = false;
		table [4][14] = false;
		table [4][15] = false;
		table [4][16] = false;
		table [4][17] = false;
		table [4][18] = false;
		table [4][19] = true;
		table [4][20] = false;
		table [4][21] = false;
		table [4][22] = false;
		table [4][23] = false;

		table [5][0] = false;
		table [5][1] = false;
		table [5][2] = false;
		table [5][3] = false;
		table [5][4] = false;
		table [5][5] = false;
		table [5][6] = false;
		table [5][7] = false;
		table [5][8] = false;
		table [5][9] = false;
		table [5][10] = false;
		table [5][11] = false;
		table [5][12] = false;
		table [5][13] = false;
		table [5][14] = false;
		table [5][15] = false;
		table [5][16] = false;
		table [5][17] = false;
		table [5][18] = false;
		table [5][19] = false;
		table [5][20] = false;
		table [5][21] = false;
		table [5][22] = false;
		table [5][23] = false;

		table [6][0] = true;
		table [6][1] = false;
		table [6][2] = false;
		table [6][3] = false;
		table [6][4] = false;
		table [6][5] = false;
		table [6][6] = true;
		table [6][7] = false;
		table [6][8] = false;
		table [6][9] = false;
		table [6][10] = false;
		table [6][11] = false;
		table [6][12] = false;
		table [6][13] = false;
		table [6][14] = false;
		table [6][15] = false;
		table [6][16] = false;
		table [6][17] = false;
		table [6][18] = false;
		table [6][19] = false;
		table [6][20] = false;
		table [6][21] = true;
		table [6][22] = true;
		table [6][23] = false;

		table [7][0] = false;
		table [7][1] = true;
		table [7][2] = true;
		table [7][3] = true;
		table [7][4] = false;
		table [7][5] = false;
		table [7][6] = false;
		table [7][7] = true;
		table [7][8] = false;
		table [7][9] = false;
		table [7][10] = false;
		table [7][11] = false;
		table [7][12] = false;
		table [7][13] = false;
		table [7][14] = false;
		table [7][15] = true;
		table [7][16] = false;
		table [7][17] = false;
		table [7][18] = false;
		table [7][19] = true;
		table [7][20] = false;
		table [7][21] = false;
		table [7][22] = false;
		table [7][23] = false;

		table [8][0] = false;
		table [8][1] = true;
		table [8][2] = true;
		table [8][3] = true;
		table [8][4] = false;
		table [8][5] = false;
		table [8][6] = false;
		table [8][7] = false;
		table [8][8] = true;
		table [8][9] = false;
		table [8][10] = false;
		table [8][11] = false;
		table [8][12] = false;
		table [8][13] = false;
		table [8][14] = false;
		table [8][15] = true;
		table [8][16] = false;
		table [8][17] = false;
		table [8][18] = false;
		table [8][19] = true;
		table [8][20] = false;
		table [8][21] = false;
		table [8][22] = false;
		table [8][23] = false;

		table [9][0] = false;
		table [9][1] = true;
		table [9][2] = true;
		table [9][3] = true;
		table [9][4] = false;
		table [9][5] = false;
		table [9][6] = false;
		table [9][7] = false;
		table [9][8] = false;
		table [9][9] = false;
		table [9][10] = false;
		table [9][11] = false;
		table [9][12] = false;
		table [9][13] = false;
		table [9][14] = false;
		table [9][15] = true;
		table [9][16] = false;
		table [9][17] = false;
		table [9][18] = false;
		table [9][19] = true;
		table [9][20] = false;
		table [9][21] = false;
		table [9][22] = false;
		table [9][23] = false;

		table [10][0] = false;
		table [10][1] = true;
		table [10][2] = true;
		table [10][3] = true;
		table [10][4] = false;
		table [10][5] = false;
		table [10][6] = false;
		table [10][7] = false;
		table [10][8] = false;
		table [10][9] = false;
		table [10][10] = false;
		table [10][11] = false;
		table [10][12] = false;
		table [10][13] = false;
		table [10][14] = false;
		table [10][15] = true;
		table [10][16] = false;
		table [10][17] = false;
		table [10][18] = false;
		table [10][19] = true;
		table [10][20] = false;
		table [10][21] = false;
		table [10][22] = false;
		table [10][23] = false;

		table [11][0] = false;
		table [11][1] = true;
		table [11][2] = true;
		table [11][3] = true;
		table [11][4] = false;
		table [11][5] = false;
		table [11][6] = false;
		table [11][7] = false;
		table [11][8] = false;
		table [11][9] = false;
		table [11][10] = false;
		table [11][11] = false;
		table [11][12] = false;
		table [11][13] = false;
		table [11][14] = false;
		table [11][15] = true;
		table [11][16] = false;
		table [11][17] = false;
		table [11][18] = false;
		table [11][19] = true;
		table [11][20] = false;
		table [11][21] = false;
		table [11][22] = false;
		table [11][23] = false;

		table [12][0] = false;
		table [12][1] = true;
		table [12][2] = true;
		table [12][3] = true;
		table [12][4] = false;
		table [12][5] = false;
		table [12][6] = false;
		table [12][7] = false;
		table [12][8] = false;
		table [12][9] = false;
		table [12][10] = false;
		table [12][11] = false;
		table [12][12] = false;
		table [12][13] = false;
		table [12][14] = false;
		table [12][15] = false;
		table [12][16] = false;
		table [12][17] = true;
		table [12][18] = false;
		table [12][19] = true;
		table [12][20] = false;
		table [12][21] = false;
		table [12][22] = false;
		table [12][23] = false;

		table [13][0] = false;
		table [13][1] = true;
		table [13][2] = true;
		table [13][3] = true;
		table [13][4] = false;
		table [13][5] = false;
		table [13][6] = false;
		table [13][7] = false;
		table [13][8] = false;
		table [13][9] = false;
		table [13][10] = false;
		table [13][11] = false;
		table [13][12] = false;
		table [13][13] = false;
		table [13][14] = true;
		table [13][15] = false;
		table [13][16] = false;
		table [13][17] = false;
		table [13][18] = false;
		table [13][19] = false;
		table [13][20] = false;
		table [13][21] = false;
		table [13][22] = false;
		table [13][23] = false;

		table [14][0] = false;
		table [14][1] = false;
		table [14][2] = false;
		table [14][3] = false;
		table [14][4] = false;
		table [14][5] = false;
		table [14][6] = true;
		table [14][7] = false;
		table [14][8] = false;
		table [14][9] = false;
		table [14][10] = false;
		table [14][11] = false;
		table [14][12] = true;
		table [14][13] = true;
		table [14][14] = false;
		table [14][15] = false;
		table [14][16] = true;
		table [14][17] = false;
		table [14][18] = false;
		table [14][19] = false;
		table [14][20] = false;
		table [14][21] = false;
		table [14][22] = false;
		table [14][23] = false;

		table [15][0] = true;
		table [15][1] = true;
		table [15][2] = true;
		table [15][3] = true;
		table [15][4] = false;
		table [15][5] = false;
		table [15][6] = false;
		table [15][7] = false;
		table [15][8] = false;
		table [15][9] = false;
		table [15][10] = false;
		table [15][11] = false;
		table [15][12] = false;
		table [15][13] = false;
		table [15][14] = false;
		table [15][15] = true;
		table [15][16] = false;
		table [15][17] = false;
		table [15][18] = false;
		table [15][19] = false;
		table [15][20] = false;
		table [15][21] = false;
		table [15][22] = false;
		table [15][23] = false;

		table [16][0] = false;
		table [16][1] = false;
		table [16][2] = false;
		table [16][3] = false;
		table [16][4] = false;
		table [16][5] = false;
		table [16][6] = true;
		table [16][7] = true;
		table [16][8] = true;
		table [16][9] = true;
		table [16][10] = true;
		table [16][11] = true;
		table [16][12] = false;
		table [16][13] = false;
		table [16][14] = false;
		table [16][15] = false;
		table [16][16] = true;
		table [16][17] = true;
		table [16][18] = false;
		table [16][19] = false;
		table [16][20] = true;
		table [16][21] = false;
		table [16][22] = false;
		table [16][23] = false;

		table [17][0] = true;
		table [17][1] = true;
		table [17][2] = true;
		table [17][3] = true;
		table [17][4] = false;
		table [17][5] = false;
		table [17][6] = false;
		table [17][7] = false;
		table [17][8] = false;
		table [17][9] = false;
		table [17][10] = false;
		table [17][11] = false;
		table [17][12] = false;
		table [17][13] = false;
		table [17][14] = false;
		table [17][15] = true;
		table [17][16] = false;
		table [17][17] = false;
		table [17][18] = false;
		table [17][19] = false;
		table [17][20] = false;
		table [17][21] = true;
		table [17][22] = false;
		table [17][23] = false;

		table [18][0] = true;
		table [18][1] = false;
		table [18][2] = false;
		table [18][3] = false;
		table [18][4] = false;
		table [18][5] = false;
		table [18][6] = true;
		table [18][7] = false;
		table [18][8] = false;
		table [18][9] = false;
		table [18][10] = false;
		table [18][11] = false;
		table [18][12] = false;
		table [18][13] = false;
		table [18][14] = false;
		table [18][15] = false;
		table [18][16] = false;
		table [18][17] = false;
		table [18][18] = false;
		table [18][19] = false;
		table [18][20] = false;
		table [18][21] = false;
		table [18][22] = true;
		table [18][23] = false;

		table [19][0] = false;
		table [19][1] = true;
		table [19][2] = true;
		table [19][3] = true;
		table [19][4] = true;
		table [19][5] = true;
		table [19][6] = true;
		table [19][7] = true;
		table [19][8] = true;
		table [19][9] = true;
		table [19][10] = true;
		table [19][11] = true;
		table [19][12] = true;
		table [19][13] = true;
		table [19][14] = true;
		table [19][15] = true;
		table [19][16] = true;
		table [19][17] = true;
		table [19][18] = true;
		table [19][19] = false;
		table [19][20] = true;
		table [19][21] = false;
		table [19][22] = false;
		table [19][23] = false;

		table [20][0] = true;
		table [20][1] = true;
		table [20][2] = true;
		table [20][3] = true;
		table [20][4] = false;
		table [20][5] = false;
		table [20][6] = false;
		table [20][7] = false;
		table [20][8] = false;
		table [20][9] = false;
		table [20][10] = false;
		table [20][11] = false;
		table [20][12] = false;
		table [20][13] = false;
		table [20][14] = false;
		table [20][15] = true;
		table [20][16] = false;
		table [20][17] = false;
		table [20][18] = false;
		table [20][19] = false;
		table [20][20] = true;
		table [20][21] = false;
		table [20][22] = false;
		table [20][23] = false;

		table [21][0] = false;
		table [21][1] = true;
		table [21][2] = true;
		table [21][3] = true;
		table [21][4] = false;
		table [21][5] = false;
		table [21][6] = false;
		table [21][7] = false;
		table [21][8] = false;
		table [21][9] = false;
		table [21][10] = false;
		table [21][11] = false;
		table [21][12] = false;
		table [21][13] = false;
		table [21][14] = false;
		table [21][15] = true;
		table [21][16] = false;
		table [21][17] = false;
		table [21][18] = false;
		table [21][19] = false;
		table [21][20] = false;
		table [21][21] = false;
		table [21][22] = false;
		table [21][23] = false;

		table [22][0] = false;
		table [22][1] = true;
		table [22][2] = true;
		table [22][3] = false;
		table [22][4] = false;
		table [22][5] = false;
		table [22][6] = false;
		table [22][7] = false;
		table [22][8] = false;
		table [22][9] = false;
		table [22][10] = false;
		table [22][11] = false;
		table [22][12] = false;
		table [22][13] = false;
		table [22][14] = false;
		table [22][15] = false;
		table [22][16] = false;
		table [22][17] = false;
		table [22][18] = false;
		table [22][19] = false;
		table [22][20] = false;
		table [22][21] = false;
		table [22][22] = false;
		table [22][23] = false;

		table [23][0] = false;
		table [23][1] = false;
		table [23][2] = false;
		table [23][3] = false;
		table [23][4] = false;
		table [23][5] = false;
		table [23][6] = false;
		table [23][7] = false;
		table [23][8] = false;
		table [23][9] = false;
		table [23][10] = false;
		table [23][11] = false;
		table [23][12] = false;
		table [23][13] = false;
		table [23][14] = false;
		table [23][15] = false;
		table [23][16] = false;
		table [23][17] = false;
		table [23][18] = false;
		table [23][19] = false;
		table [23][20] = false;
		table [23][21] = false;
		table [23][22] = false;
		table [23][23] = false;

		if (table[row][col]){
			// System.out.println("TRUE");
			return true;
		}else{
			// System.out.println("FALSE");
			return false;
		}
	}
}
