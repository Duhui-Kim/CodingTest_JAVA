import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    private static int safeZone;

    // D, U, R, L
    private static int[] dx = {1, -1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행과 열의 크기
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // map과 check 배열도 생성
        char[][] map = new char[N][M];
        int[][] check = new int[N][M];

        // map 입력 받기
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // DFS 진행을 위한 stack 선언
        Stack<int[]> stack = new Stack<>();

        // safeZone의 수와 방향 선언
        safeZone = 0;
        int dir = 0;
        int mark = 1;

        // map을 순회하며 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 이미 지나간 곳이면 지나감
                if(check[i][j] != 0) continue;

                // 지나가지 않은 곳이면 체크하고 safeZone 1개 증가
                check[i][j] = mark;
                safeZone++;

                // 해당 좌표를 stack에 넣고 DFS 시작
                int[] arr = new int[2];
                arr[0] = i;
                arr[1] = j;
                stack.push(arr);

                DFS(map, stack, check, mark);
                mark++;
            }
        }
        System.out.println(safeZone);
    }

    private static void DFS(char[][] map, Stack<int[]> stack, int[][] check, int cur) {
        while (!stack.isEmpty()) {
            int[] arr = stack.pop();
            int x = arr[0];
            int y = arr[1];

            int dir = 0;

            // 방향에 따라 dir 방향 바꿔줌
            switch (map[x][y]) {
                case 'D':
                    dir = 0;
                    break;
                case 'U':
                    dir = 1;
                    break;
                case 'R':
                    dir = 2;
                    break;
                case 'L':
                    dir = 3;
                    break;
            }

            // 해당 방향으로 이동시킴
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            // 만난 숫자가 나의 숫자가 아니면 다른 루프와 연결되므로 safeZone 한개면 된다.
            if (check[nx][ny] != 0 && check[nx][ny] != cur) {
                safeZone--;
                break;
            } else if (check[nx][ny] == cur) {
                break;
            }

            // 체크되어있지 않다면 체크하고 다음 방향
            check[nx][ny] = cur;
            arr = new int[2];
            arr[0] = nx;
            arr[1] = ny;
            stack.push(arr);
        }
    }
}