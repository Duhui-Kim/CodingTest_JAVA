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
		
		N = Integer.parseInt(br.readLine());
		
		chess = new ArrayList<>();
		
		backTracking(0);
		
		System.out.println(cnt);
	}

	private static void backTracking(int k) {
		if(k==N) {
			cnt++;
			return;
		}
		
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
			if(i == x || y == chess.get(i) || i + chess.get(i) == x + y || i - x == chess.get(i) - y) {
				return false;
			}
		}
		return true;
	}
}
