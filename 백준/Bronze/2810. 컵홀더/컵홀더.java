import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// 커플석의 수를 구하면 풀기 편할 것 같았다.
		int people = sc.nextInt();
		char[] seat = sc.next().toCharArray();
		
		// 싱글석의 수
		int sCnt = 0;
		for(char c : seat) {
			if(c == 'S') {
				sCnt++;
			}
		}
		
		// (전체 인원수 - 싱글석 수) / 2의 값이 커플석의 수이고
		int lCnt = (people - sCnt) / 2;
		
		// 이 때의 컵홀더의 수는 (전체인원수+1 - 커플석의 수)이다.
		int cHolder = people+1 - lCnt;
		
		// 컵홀더의 수 > 인원수이면 인원수 반환
		if(cHolder>people) {
			System.out.println(people);
		} else {
		// 그렇지 않을 경우 컵홀더의 수 반환
			System.out.println(cHolder);
		}
		
	}
}
