import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Main {
	public static int maxPoint;
	public static String maxString;
	public static int maxLength;
	public static Node ROOT;
	public static HashSet<String> set;
	
	public static class Node {
		char c;
		boolean end = false;
		boolean check = false;
		HashMap<Character, Node> child = new HashMap<>();
		
		public Node(char c) {
			this.c = c;
		}
	}
	
	public static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
	public static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		ROOT = new Node('\0');
		
		for(int i=0; i<N; i++) {
			char[] input = br.readLine().toCharArray();
			
			Node cur = ROOT;
			for(int j=0; j<input.length; j++) {
				if(cur.child.get(input[j]) == null) {
					cur.child.put(input[j], new Node(input[j]));
					cur = cur.child.get(input[j]);
				} else {
					cur = cur.child.get(input[j]);
				}
			}
			cur.end = true;
		}
		
		br.readLine(); // 공백
		
		int testCase = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc=0; tc<testCase; tc++) {
			char[][] boggle = new char[4][4];
			
			for(int i=0; i<4; i++) {
				boggle[i] = br.readLine().toCharArray();
			}
			
			boolean[][] check = new boolean[4][4];
			maxPoint = 0;
			maxLength = 0;
			set = new HashSet<String>();
			
			for(int i=0; i<4; i++) {
				for(int j=0; j<4; j++) {
					if(ROOT.child.get(boggle[i][j]) == null) continue;
					
					check[i][j] = true;
					backTracking(boggle, check, ROOT.child.get(boggle[i][j]), i, j, String.valueOf(boggle[i][j]), 1);
					check[i][j] = false;
				}
			}
			
			sb.append(String.format("%d %s %d\n", maxPoint, maxString, set.size()));
			
			if(tc != testCase-1)
				br.readLine();
		}
		System.out.println(sb);
	}

	private static void backTracking(char[][] boggle, boolean[][] check, Node cur, int x, int y, String tmp, int length) {
		// 현재 지점에 끝표시가 되어있을 때
		if(cur.end) {
			if(maxLength < length) {
				maxLength = length;
				maxString = tmp;
			} else if (maxLength == length) {
				String[] a = new String[] {maxString, tmp};
				Arrays.sort(a);
				maxString = a[0];
			}
			
			if(!set.contains(tmp)) {
				maxPoint += points[length];
			}
			set.add(tmp);
		}
		
		// 다음 지점 찾아가기
		for(int i=0; i<8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= 4 || ny >= 4) continue;
			if(check[nx][ny]) continue;
			if(cur.child.get(boggle[nx][ny]) == null) continue;
			
			check[nx][ny] = true;
			backTracking(boggle, check, cur.child.get(boggle[nx][ny]), nx, ny, tmp + boggle[nx][ny], length+1);
			check[nx][ny] = false;
		}
	}
}