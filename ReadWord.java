public class ReadWord{
	public static void main(String args[]){
		String input = "The quick   brown fox  jumps over   the     lazy dog";
		String[] split = input.split("\\s+");

		for (int i = 0; i < split.length; i++){
			System.out.println(split[i]);
		}
	}
}