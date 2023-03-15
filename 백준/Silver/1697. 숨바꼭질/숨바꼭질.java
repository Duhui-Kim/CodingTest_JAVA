import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N과 K를 받는다. 
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int MAX_NUM = 100001;
		
		// int 배열 하나 만듦
		int[] check = new int[MAX_NUM];
		
		// BFS를 위한 Queue도 만듦
		Queue<Integer> queue = new ArrayDeque<>();
		
		// N == K이면 0,
		if(N == K) {
			System.out.println(0);
			return;
		}
		
		// N을 Queue에 넣음
		// 반복문 실행
		queue.offer(N);

		while(!queue.isEmpty()) {
			// Queue에서 poll()하고
			int a = queue.poll();
			for(int i=0; i<3; i++) {
				int cx;
				if(i==0) cx=a+1;
				else if(i==1) cx=a-1;
				else cx=a*2;
				// N에 -1, 1, N을 더한 값이 0이상 MAX_NUM미만일 때
				if(cx != N && cx >= 0 && cx < MAX_NUM && check[cx] == 0) {
					// K와 같은 수가 있으면 check[cx]에 check[a]+1 break; 없으면 check[cx]에 check[a]+1을 넣고 queue에 cx 넣음;
					if(cx == K) {
						check[cx] = check[a]+1;
						System.out.println(check[cx]);
						return;
					// -1, 1, N을 더한 값을 Queue에 넣음
					} else {
						check[cx] = check[a]+1;
						queue.offer(cx);
					}
				}
			}
		}
	}
}
