import java.util.*;

public class Matching{
	public static void main(String args[]){
		String a = "{{{{{(((((((((()))))))))){{{{{<<<<<<<<<<>>>>>>>>>>}}}}[[[[[[[[[[]]]]]]]]]]}}}}}}";

		Stack<Character> stack = new Stack<Character>();
		int index = 0;

		while (index < a.length()){
			char c = a.charAt(index);

			if (c == '}' || c == ']' || c == '>' || c == ')'){
				if (stack.isEmpty()){
					System.out.println("Invalid Expression");
					return;
				}else{
					if (c == '}'){
						if (stack.peek() == '{'){
							stack.pop();
						}else{
							System.out.println("Invalid Expression");
							return;
						}
					}

					else if (c == ']'){
						if (stack.peek() == '['){
							stack.pop();
						}else{
							System.out.println("Invalid Expression");
							return;
						}
					}

					else if (c == ')'){
						if (stack.peek() == '('){
							stack.pop();
						}else{
							System.out.println("Invalid Expression");
							return;
						}
					}

					if (c == '>'){
						if (stack.peek() == '<'){
							stack.pop();
						}else{
							System.out.println("Invalid Expression");
							return;
						}
					}
				}
			}

			if (c == '{' || c == '(' || c == '<' || c == '[')
				stack.push(c);

			index++;
		}

		if (stack.isEmpty())
			System.out.println("Valid Expression");
	}
}