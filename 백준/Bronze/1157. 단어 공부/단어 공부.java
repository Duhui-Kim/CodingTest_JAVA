
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		
		int[] abcList = new int[26];
		
		for(int i=0; i<str.length(); i++) {
			if(str.charAt(i) > 95) {
				abcList[str.charAt(i)-32-65]++;
			} else {
				abcList[str.charAt(i)-65]++;
			}
		}
		int max = 0;
		int maxIdx = 0;
		boolean isMax = false;
		for(int i=0; i<abcList.length; i++) {
			if(abcList[i] > max) {
				max = abcList[i];
				maxIdx = i;
				isMax = false;
			} else if (!isMax && abcList[i] == max){
				isMax = true;
			}
		}
		if(isMax) {
			System.out.println("?");
		} else {
			maxIdx += 65;
			System.out.println((char)maxIdx);
		}
	}
}