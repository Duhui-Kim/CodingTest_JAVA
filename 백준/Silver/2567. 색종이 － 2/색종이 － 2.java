import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 색종이를 붙일 도화지 배열을 만든다.
        int[][] paper = new int[101][101];

        // 색종이의 수를 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // N개의 색종이의 왼쪽아래 좌표를 입력받고 도화지에 그린다.
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int j = x; j < x+10; j++) {
                for (int k = y; k < y+10; k++) {
                    paper[j][k] = 1;
                }
            }
        }

        // 4방 탐색을 위한 delta 배열을 만든다.
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // 모서리 길이를 0으로 초기화하고,
        // 1을 만났을 때, 주변 좌표에서 0을 만나면 cnt를 올리고 break한다.
        int answer = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                if(paper[i][j] == 1) {
                    int cntZero = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if(nx >= 0 && ny >= 0 && nx < 101 && ny < 101) {
                            if(paper[nx][ny] == 0) {
                                cntZero++;
                            }
                        }
                    }
                    // 꼭짓점의 경우 2번 세줘야 값이 일치..
                    if(cntZero == 2) answer += 2;
                    else if (cntZero == 1) answer += 1;
                }
            }
        }
        System.out.println(answer);
    }
}
