import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 행의 길이, 열의 길이, 주어지는 사각형의 개수를 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        int[][] map = new int[N][M];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int c1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            for (int j = r1; j < r2; j++) {
                for (int k = c1; k < c2; k++) {
                    map[j][k] = 1;
                }
            }
        }

        // BFS에 사용할 queue와 area size 저장할 list 선언
        Queue<int[]> queue = new LinkedList<>();
        List<Integer> area = new ArrayList<>();

        // 사각형 숫자 세는 용도
        int cnt = 0;

        // BFS에 사용할 델타
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        // 전체 순회하며 BFS 진행
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                // 0을 만나면 size는 1부터 시작, 영역의 수 cnt도 증가
                if(map[i][j] == 0) {
                    int size = 1;
                    cnt++;
                    map[i][j] = 1;
                    int[] arr = new int[2];
                    arr[0] = i;
                    arr[1] = j;
                    queue.offer(arr);

                    // queue에서 꺼낸 값의 좌표가 0일 경우 size는 계속 증가
                    while (!queue.isEmpty()) {
                        int x = queue.peek()[0];
                        int y = queue.poll()[1];
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                            if(map[nx][ny] == 0) {
                                size++;
                                map[nx][ny] = 1;
                                arr = new int[2];
                                arr[0] = nx;
                                arr[1] = ny;
                                queue.offer(arr);
                            }
                        }
                    }
                    // 최종적으로 size 저장
                    area.add(size);
                }
            }
        }
        // size를 오름차순으로 정렬
        Collections.sort(area);

        // 영역의 개수와 size를 출력
        StringBuilder sb = new StringBuilder();
        sb.append(cnt).append("\n");
        for (int a : area) {
            sb.append(a + " ");
        }
        System.out.println(sb);
    }
}