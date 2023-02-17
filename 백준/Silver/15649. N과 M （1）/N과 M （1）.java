import java.io.*;
import java.util.*;

public class Main {
	
	// check, arr, N, M, bw는 모두 method 안에서도 쓰이므로 static으로 선언한다.
	private static boolean[] check;
	private static int M;
	private static int N;
	private static int[] arr;
	private static BufferedWriter bw;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N과 M을 입력받음
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 중복 방문을 막기 위한 배열 check를 만들고
		check = new boolean[N];
		
		// 숫자를 저장해줄 배열 arr을 만든다.
		arr = new int[M];
		
		// 0부터 backTracking을 진행한다.
		backTracking(0);
		
		// 결과값을 출력한다.
		bw.close();
	}
	
	private static void backTracking(int k) throws IOException {
		// k == M이면 arr가 다 찬 것이기 때문에 인자를 bw에 옮긴다.
		if(k == M) {
			for(int i : arr) {
				bw.write(i + " ");
			}
			bw.write("\n");
			return;
		}
		
		// k가 M보다 작은 경우, k가 arr의 idx가 되어 숫자를 입력받는다.
		// 동일한 숫자가 입력되지 않도록 check를 통해 관리한다.
		for(int i=0; i<N; i++) {
			if(!check[i]) {
				arr[k] = i+1;
				
				check[i] = true;
				backTracking(k+1);
				check[i] = false;
			}
		}
	}
}
