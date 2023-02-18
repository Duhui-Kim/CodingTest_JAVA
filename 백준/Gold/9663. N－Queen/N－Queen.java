import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	
	private static int N;
	private static int cnt = 0;
	private static List<Integer> chess;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 사이즈 N과 chess 위치를 표시할 리스트를 만듦
		N = Integer.parseInt(br.readLine());
		chess = new ArrayList<>();
		
		// 백트래킹 시작
		backTracking(0);
		
		// 체스 둘 수 있는 개수를 출력
		System.out.println(cnt);
	}

	private static void backTracking(int k) {
		// N개가 모두 배치되면 끝!!
		if(k==N) {
			cnt++;
			return;
		}
		
		// chess의 idx가 열, check의 idx의 value가 행!!
		// 이전에 찍었던 값들과 비교해서 둘 수 있는 자리이면 둔다.
		for(int i=0; i<N; i++) {
			if(check(k, i)) {
				chess.add(i);
				
				backTracking(k+1);
				
				chess.remove((Integer)i);
			}
		}
		
	}
	private static boolean check(int x, int y) {
		for(int i=0; i<chess.size(); i++) {
			// 행, 열 일치 시 못두고, 대각선에도 못둠
			if(i == x || y == chess.get(i) || i + chess.get(i) == x + y || i - x == chess.get(i) - y) {
				return false;
			}
		}
		// 일치하지 않으면 true 반환
		return true;
	}
}
