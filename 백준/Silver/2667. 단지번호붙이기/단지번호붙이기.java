import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 지도의 크기를 입력받고 해당 크기만큼의 지도를 만든다.
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];

        // 집을 입력받는다.
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // BFS 진행용 queue와 정답 저장용 list 선언
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> list = new ArrayList<>();

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // map에서 1을 만나면 BFS 시작
                if(map[i][j] == '1') {

                    // size를 측정하기 위해 cnt 인자 생성
                    int cnt = 1;

                    // 다음에 또 방문하지 않기 위해 해당 좌표 0으로 설정
                    map[i][j] = '0';

                    // queue에 좌표를 넣어준다.
                    queue.offer(i);
                    queue.offer(j);

                    // queue에 남아있을 때까지 BFS 진행
                    while (!queue.isEmpty()) {
                        int x = queue.poll();
                        int y = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                            if(map[nx][ny] != '1') continue;
                            cnt++;
                            map[nx][ny] = '0';
                            queue.offer(nx);
                            queue.offer(ny);
                        }
                    }
                    // BFS가 종료되면 1의 개수인 cnt를 저장
                    list.add(cnt);
                }
            }
        }
        // 배열 순회가 완료되면 모든 단지를 찾았고, 사이즈도 측정했다.
        Collections.sort(list);

        StringBuilder sb = new StringBuilder();

        sb.append(list.size()).append("\n");
        for (int a : list) {
            sb.append(a).append("\n");
        }
        System.out.println(sb.toString());
    }
}
