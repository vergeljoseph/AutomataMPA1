import java.io.*;

public class Main {
  public static void main(String[] args) throws Exception {
  	FileInputStream fs= new FileInputStream("input.txt");
	BufferedReader br = new BufferedReader(new InputStreamReader(fs));
	File file = new File("output.txt");
  	file.createNewFile();
    FileWriter fileWriter = new FileWriter(file, true);

	String str;
	while ((str = br.readLine()) != null) {
		System.out.println(str);
		String[] split = str.split("\\s+");

		for (int i = 0; i < split.length; i++){
			fileWriter.append(split[i] + "\n");
		}
	}
    fileWriter.close(); 
  }
}