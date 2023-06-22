import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		HashMap<String, String[]> regist = new HashMap<>();
		
		regist.put("black", new String[] {"0", ""});
		regist.put("brown", new String[] {"1", "0"});
		regist.put("red", new String[] {"2", "00"});
		regist.put("orange", new String[] {"3", "000"});
		regist.put("yellow", new String[] {"4", "0000"});
		regist.put("green", new String[] {"5", "00000"});
		regist.put("blue", new String[] {"6", "000000"});
		regist.put("violet", new String[] {"7", "0000000"});
		regist.put("grey", new String[] {"8", "00000000"});
		regist.put("white", new String[] {"9", "000000000"});
		
		String color1 = br.readLine();
		String color2 = br.readLine();
		String color3 = br.readLine();
		
		if(color1.equals("black")) {
			if(color2.equals("black")) {
				System.out.println(0);
			} else {
				System.out.println(regist.get(color2)[0] + regist.get(color3)[1]);
			}
		} else {
			System.out.println(regist.get(color1)[0] + regist.get(color2)[0] + regist.get(color3)[1]);
		}
		
	}
}