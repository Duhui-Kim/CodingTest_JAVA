import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		// map을 입력받음
		char[][] map = new char[N][M];
		for(int i=0; i<N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		// 먼저 X를 찾고 사방탐색 -> . 개수 3 이상이면 A로 바꿈
		int[] dx = {-1, 1, 0, 0};
		int[] dy = {0, 0, -1, 1};
		
		// 2차 반복을 통해 X를 찾는데, 왼쪽위 꼭짓점과 오른쪽아래 꼭짓점을 찾아야하므로
		// x의 min값과 max값, y의 min값과 max값을 저장
		int xMin = N;
		int xMax = 0;
		int yMin = M;
		int yMax = 0;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j] == 'X') {
					int seaCnt = 0;
					for(int k=0; k<4; k++) {
						int x = i + dx[k];
						int y = j + dy[k];

						// 범위 안에서 .은 바다임
						if(x >= 0 && y >= 0 && x < N && y < M) {
							if(map[x][y] == '.') {
								seaCnt++;
							}
						
						// 범위 밖도 바다임!
						} else {
							seaCnt++;
						}
					}
					
					// 3 이상일 경우 잠김.
					if(seaCnt >= 3) {
						map[i][j] = 'A';
						
					// 잠기지 않은 육지의 경우 xmin, xmax, ymin, ymax값 비교 후 저장
					} else {
						if(i > xMax) xMax = i;
						if(i < xMin) xMin = i;
						if(j > yMax) yMax = j;
						if(j < yMin) yMin = j;
					}
				}
			}
		}
		// xmin, ymin ~ xmax, ymax까지 반복문 진행하며 값 저장 후 출력
		for(int i=xMin; i<=xMax; i++) {
			for(int j=yMin; j<=yMax; j++) {
				
				// 새로 만든 map에 A가 있으면 .으로 바꿔주기
				if(map[i][j] == 'A') {
					sb.append(".");
				} else {
					sb.append(map[i][j] + "");					
				}
			}
			sb.append("\n");
		}
		// 출력
		System.out.println(sb);
	}
}
