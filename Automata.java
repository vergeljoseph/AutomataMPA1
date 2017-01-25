import java.util.*;
import java.lang.*;
import java.io.*;

public class Automata{
	public static void main(String args[]){
		String quantity[] = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
		String size[]	  = {"small", "medium-sized", "large", "tiny", "big", "life-sized", "gigantic", "tiny", "long", "short"};
		String shape[]	  = {"round", "cylindrical", "circular", "triangular"};
		String color[]	  = {"red", "blue", "yellow", "green", "purple", "brown", "black", "white"};
		String noun[]	  =	{"dog", "box", "bag", "car", "glass"};
		List validQ		  = Arrays.asList(quantity);
		List validSz	  = Arrays.asList(size);
		List validSh	  = Arrays.asList(shape);
		List validC		  = Arrays.asList(color);
		List validN		  = Arrays.asList(noun);

		int [][] states = new int [2][6];
		int row = 0, col = 0;

		Automata a = new Automata();
		a.check(row, col);
	}

	public boolean check (int row, int col){
		if (row == 0 && col == 0)
			return false;
		else if (row == 0 && col == 1)
			return true;
		else if (row == 0 && col == 2)
			return true;
		else if (row == 0 && col == 3)
			return true;
		else if (row == 0 && col == 4)
			return true;
		else if (row == 0 && col == 5)
			return true;
		else if (row == 1 && col == 0)
			return false;
		else if (row == 1 && col == 1)
			return false;
		else if (row == 1 && col == 2)
			return true;
		else if (row == 1 && col == 3)
			return true;
		else if (row == 1 && col == 4)
			return true;
		else if (row == 1 && col == 5)
			return true;
		else if (row == 2 && col == 0)
			return false;
		else if (row == 2 && col == 1)
			return false;
		else if (row == 2 && col == 2)
			return false;
		else if (row == 2 && col == 3)
			return true;
		else if (row == 2 && col == 4)
			return true;
		else if (row == 2 && col == 5)
			return true;
		else if (row == 3 && col == 0)
			return false;
		else if (row == 3 && col == 1)
			return false;
		else if (row == 3 && col == 2)
			return false;
		else if (row == 3 && col == 3)
			return false;
		else if (row == 3 && col == 4)
			return true;
		else if (row == 3 && col == 5)
			return true;
		else if (row == 4 && col == 0)
			return false;
		else if (row == 4 && col == 1)
			return false;
		else if (row == 4 && col == 2)
			return false;
		else if (row == 4 && col == 3)
			return false;
		else if (row == 4 && col == 4)
			return false;
		else if (row == 4 && col == 5)
			return true;
		else if (row == 5 && col == 0)
			return false;
		else if (row == 5 && col == 1)
			return false;
		else if (row == 5 && col == 2)
			return false;
		else if (row == 5 && col == 3)
			return false;
		else if (row == 5 && col == 4)
			return false;
		else
			return false;
	}
}