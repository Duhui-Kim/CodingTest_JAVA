import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int testCase = sc.nextInt();
		for(int t=1; t<=testCase; t++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			// pw를 저장할 set 선언
			Set<String> pw = new HashSet<>();
			
			// N개의 숫자를 입력받고
			char[] str = sc.next().toCharArray();
					
			// N/4번 다이얼을 돌릴 수 있음
			for(int k=0; k<N/4; k++) {
				// 항상 4개의 문자를 저장함
				for(int i=0; i<4; i++) {
					// N/4개의 글자를 newStr에 모아서 저장
					String newStr = "";
					for(int j = i * N/4; j < (i+1) * N/4; j++) {
						newStr += str[(j+k) % N];
					}
					pw.add(newStr);
				}
			}
			// 배열에 set 값을 저장
			String[] ans = pw.toArray(new String[0]);
			
			// 16진수를 10진수로 변환하여 저장할 배열 선언
			int[] ansInt = new int[ans.length];
			
			// ans 배열에서 문자열을 하나씩 꺼내서
			for(int i=0; i<ans.length; i++) {
				
				// 각 문자열의 0번째 ~ n번째 인덱스의 char을 하나씩 꺼냄
				// 해당 값이 숫자이면 ansInt에 저장하고, 문자이면 10진수 값으로 바꿔서 저장
				// 돌아올 때마다 16을 곱해줌.
				for(int j=0; j < ans[i].length(); j++) {
					ansInt[i] *= 16;
	
					if(ans[i].charAt(j) - '0' > 10) {
						ansInt[i] += ans[i].charAt(j) - '7';
					} else {
						ansInt[i] += ans[i].charAt(j) - '0';
					}
				}
			}
			// 숫자 크기대로 정렬하고 오름차순으로 정렬되어있으므로 뒤에서 K번째 수를 출력
			Arrays.sort(ansInt);
			
			sb.append("#" + t + " " + ansInt[ansInt.length-K] + "\n");
		}
		System.out.println(sb);
	}
}
