import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	private static char[][] tree;
	private static int n;
	private static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		
		n = Integer.parseInt(br.readLine());		
		// 2차원 배열을 만들어서 3칸짜리 배열 N개를 입력받음
		tree = new char[n][3];
		
		for(int i=0; i<n; i++) {
			String str = br.readLine().replaceAll(" ", "");
			tree[i] = str.toCharArray();
		}
		preOrder(tree[0][0]);
		sb.append("\n");
		inOrder(tree[0][0]);
		sb.append("\n");
		postOrder(tree[0][0]);
		
		System.out.println(sb);
	}
	
	private static void preOrder(char a) {
		if(a == '.') return;
		
		for(int i=0; i<n; i++) {
			if(tree[i][0] == a) {
				// 전위순회는 맨 위 -> 왼 -> 오 이므로,
				// 처음에 맨 위를 저장 -> 왼쪽 -> 오른쪽
				sb.append(a + "");
				preOrder(tree[i][1]);
				preOrder(tree[i][2]);				
			}
		}
		
	}
	
	private static void inOrder(char a) {
		if(a == '.') return;
		
		for(int i=0; i<n; i++) {
			if(tree[i][0] == a) {
				// 중위순회는 맨 왼쪽 -> 위 -> 오 이므로,
				// 처음에 맨 왼쪽을 저장 -> 위쪽 -> 오른쪽
				inOrder(tree[i][1]);			
				sb.append(a + "");
				inOrder(tree[i][2]);				
			}
		}
	}
	
	private static void postOrder(char a) {
		if(a == '.') return;
		
		for(int i=0; i<n; i++) {
			if(tree[i][0] == a) {
				// 후위순회는 맨 왼쪽 -> 오른쪽 -> 위 이므로,
				// 처음에 맨 왼쪽을 저장 -> 오른쪽 -> 위 저장이므로
				// post에서 다 빠져나오면서 저장
				postOrder(tree[i][1]);			
				postOrder(tree[i][2]);				
			}
		}
		sb.append(a + "");
	}
}
