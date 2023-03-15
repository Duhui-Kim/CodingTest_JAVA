import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // map의 크기 N과 경사로의 크기 L을 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        // map을 선언
        int[][] map = new int[N][N];

        // map에 숫자를 입력받는다.
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 여기서부터 method 진행
        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += checkCol(map, i, N, L);
            answer += checkRow(map, i, N, L);
        }
        System.out.println(answer);
    }

    private static int checkRow(int[][] map, int row, int n, int l) {
        int cnt = 1;
        boolean down = false;
        int cur = map[row][0];

        // 행의 처음부터 끝까지 진행한다.
        for (int i = 1; i < n; i++) {
            // 현재 값과 다음 값이 같을 때 cnt를 증가시킨다.
            if(map[row][i] == cur) {
                cnt++;
            // 현재 값보다 1 작은 값이 들어오면 down을 true로 바꾸고 cur을 해당 값으로 바꾼다.
            // 이미 down이 true였다면 종료
            } else if (map[row][i] + 1 == cur) {
                if(down) break;
                cnt = 1;
                cur = map[row][i];
                down = true;

            // 현재 값보다 1 큰 값이 들어왔을 때 cnt 값이 l보다 크면 1로 초기화시키고 경사로 위로 올린다.
            // cnt가 l보다 작거나 down이 true인 상태면 종료
            } else if (map[row][i] - 1 == cur) {
                if(down) break;
                if(cnt >= l) {
                    cnt = 1;
                    cur = map[row][i];
                } else {
                    down = true;
                    break;
                }
            // 위의 경우가 아니면 2 이상 차이나는 것이므로 종료
            } else {
                down = true;
                break;
            }
            // down이 true였다면, cnt가 l 이상일 때 cnt를 0으로 만든다.
            if(down && cnt >= l) {
                down = false;
                cnt = 0;
            }
        }

        // down이 true일 경우 0 반환, false일 경우 1 반환
        if (down) return 0;
        else return 1;
    }

    private static int checkCol(int[][] map, int col, int n, int l) {
        int cnt = 1;
        boolean down = false;
        int cur = map[0][col];

        // 열의 처음부터 끝까지 진행한다.
        for (int i = 1; i < n; i++) {
            // 현재 값과 다음 값이 같을 때 cnt를 증가시킨다.
            if(map[i][col] == cur) {
                cnt++;
            // 현재 값보다 1 작은 값이 들어오면 down을 true로 바꾸고 cur을 해당 값으로 바꾼다.
            // cnt도 1부터 시작
            // 이미 down이 true였다면 종료
            } else if (map[i][col] + 1 == cur) {
                if(down) break;
                cnt = 1;
                cur = map[i][col];
                down = true;

            // 현재 값보다 1 큰 값이 들어왔을 때 cnt 값이 l보다 크면 1로 초기화시키고 경사로 위로 올린다.
            // cnt가 l보다 작거나 down이 true인 상태면 종료
            } else if (map[i][col] - 1 == cur) {
                if(down) break;
                if(cnt >= l) {
                    cnt = 1;
                    cur = map[i][col];
                } else {
                    down = true;
                    break;
                }
            // 위의 경우가 아니면 2 이상 차이나는 것이므로 종료
            } else {
                down = true;
                break;
            }
            // down이 true였다면, cnt가 l 이상일 때 cnt를 0으로 만든다.
            if(down && cnt >= l) {
                down = false;
                cnt = 0;
            }
        }

        // down이 true일 경우 0 반환, false일 경우 1 반환
        if (down) return 0;
        else return 1;
    }
}