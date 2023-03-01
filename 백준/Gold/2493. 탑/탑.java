package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 빌딩의 수 N을 입력받는다.
		int N = Integer.parseInt(br.readLine());
		
		// 정답을 저장할 int 배열을 만든다
		int[] answer = new int[N];
		
		// 빌딩을 저장해둘 stack을 선언한다.
		// int[]의 0번 idx에는 빌딩의 크기, 1번 idx에는 빌딩의 idx를 저장
		Stack<int[]> stack = new Stack<>();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// N번 반복하며 빌딩을 입력받는다.
		for(int i=0; i<N; i++) {
			int building = Integer.parseInt(st.nextToken());
			
			// stack이 비어있지 않을 때 비교 진행
			// stack이 빌 동안 더 큰 빌딩을 못찾았다면, 0인 상태로 남겨짐
			while(!stack.isEmpty()) {
				// stack의 맨 위의 값이 빌딩보다 크면,
				// 정답 배열에 stack에 있는 빌딩의 idx를 저장하고 while문을 빠져나온다.
				if(stack.peek()[0] > building) {
					answer[i] = stack.peek()[1];
					break;
				} else {
				// stack에 있는 빌딩이 더 작으면 stack에서 하나를 제거한다.
					stack.pop();
				}
			}
			
			// 빌딩의 크기, 위치를 포함한 정보를 stack에 저장
			int[] save = new int[2];
			save[0] = building;
			save[1] = i+1;
			stack.push(save);
		}
		// answer의 숫자를 차례로 출력한다.
		for(int a : answer) {
			bw.write(a + " ");
		}
		bw.close();
	}
}
