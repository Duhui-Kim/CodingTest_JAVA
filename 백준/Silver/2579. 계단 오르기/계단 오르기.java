import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader bw = new BufferedReader(new InputStreamReader(System.in));
		
		// 계단 수 n을 입력받고 계단 점수까지 입력받음
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		
		
		// n이 0이면 0 출력
		if(n==0) {
			System.out.println(0);
			return;
		// n이 1이면 arr[0] 출력;
		} else if(n==1) {
			System.out.println(arr[0]);
			return;
		// n이 2이면 arr[0] + arr[1] 출력
		} else if(n==2) {
			System.out.println(arr[0] + arr[1]);
			return;
		}
		
		// 그렇지 않을 경우,
		
		// 답을 저장해줄 배열을 만듦
		// [n][0]에는 1칸 뛰어넘어올 수 있는 값을 넣고
		// [n][1]에는 2칸 뛰에넘어올 수 있는 값을 넣음
		
		// 규칙 상 1칸 뛰어넘는건 2번이 안되기 때문에 [n][0]에는 항상 [n-1][1]의 값이 들어감
		// 2칸 뛰어넘는값인 [n][1]에는 항상 [n-2][0]과 [n-2][1] 중 큰 값이 들어감
		int[][] ans = new int[n][2];
		
		// 초기값을 정해주고,
		ans[0][0] = arr[0];
		ans[1][0] = arr[0]+arr[1];
		ans[1][1] = arr[1];
		
		for(int i=2; i<n; i++) {
			ans[i][0] = ans[i-1][1] + arr[i];
			ans[i][1] = Math.max(ans[i-2][0], ans[i-2][1]) + arr[i];
		}
		// 최종 값은 저장된 두 수 중 큰 값
		System.out.println(Math.max(ans[n-1][0], ans[n-1][1]));
	}
}
