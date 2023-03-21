import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		int testCase = sc.nextInt();
		
		for(int tc=1; tc<=testCase; tc++) {
			// 비트마스킹을 이용해서 풀어보자
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			// N 크기의 배열을 선언하고 인풋을 입력받는다.
			int[] arr = new int[N];
			for(int i=0; i<N; i++) {
				arr[i] = sc.nextInt();
			}
			
			// K와 같은 부분수열합의 개수를 세기 위한 인자 선언
			int cnt = 0;
			
			// 반복문을 도는데, N개의 비트가 필요하므로 1을 N만큼 shift 시킨다.
			for(int i=0; i< 1<<N; i++) {
				
				int sumNum = 0;
				// 비트연산 결과가 0보다 크면 해당 idx는 1로 표시되어있으므로 해당 수열값 더하기
				for(int j=0; j<N; j++) {
					if((i & 1<<j) > 0) {
						sumNum += arr[j];
					}
				}
				// 부분 수열의 합이 K와 같으면 cnt 증가
				if(sumNum == K) cnt++;
			}
			
			sb.append(String.format("#%d %d\n", tc, cnt));
		}
		System.out.println(sb);
	}
}