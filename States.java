import java.util.*;
import java.io.*;

public class States{
	public static void main(String args[])throws Exception{
		States s = new States();
		String data_types[] = {"int", "float", "double", "char"};
		String operations[] = {"+", "-", "*", "/", "%"};
		String equals[] = {"="};
		String opening[] = {"(", "[", "{"};
		String closing[] = {")", "]", "}"};
		List data = Arrays.asList(data_types);
		List operation_list = Arrays.asList(operations);
		List equals_list = Arrays.asList(equals);
		List opening_list = Arrays.asList(opening);
		List closing_list = Arrays.asList(closing);

		Queue<String> queue = new LinkedList<String>();

		FileInputStream fs= new FileInputStream("text.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(fs));
		File file = new File("output.txt");
	  	file.createNewFile();
	    FileWriter fileWriter = new FileWriter(file, true);

		String str;
		int ctr = 0;

		while ((str = br.readLine()) != null) {
			String[] split = str.split("\\s+");
			char c = str.charAt(str.length()-1);
			System.out.print("Line " + ctr +": ");

			if (split.length == 1){
				System.out.println("Invalid. Must be an expression.");	
			}else{
				if (!data.contains(split[0]) && !split[0].equals("function")){
					System.out.println("Invalid start of expression");
				}else{
					if (c == ';'){
						for (int i = 0; i < split.length; i++){
							fileWriter.append(split[i] + "\n");
							// System.out.println("Queueing " + split [i]);
							queue.add(split[i]);
						}

						while (!queue.isEmpty()){
							// System.out.println("Dequeueing " + queue.element());
							queue.remove();
						}
						System.out.print("Valid Expression!");
						System.out.println();
					}else{
						System.out.println("Invalid line of code. Missing ';' at the end of the line.");
						}
					}	
				}
				ctr++;
			}
	    fileWriter.close(); 
	}
}