import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 맵의 크기 N과 입력받을 간선의 수 M
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 맵 저장해두기
        int[][] map = new int[N+1][N+1];

        // 맵 초기화하기
        for (int i = 0; i <= N; i++) {
            for (int j = i+1; j <= N; j++) {
                map[i][j] = Integer.MAX_VALUE/2;
                map[j][i] = Integer.MAX_VALUE/2;
            }
        }

        // 간선 입력받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
        }

        // 최대 시간 넣기
        for (int k = 0; k <= N; k++) {
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    if (i == j) continue;

                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 최소시간 저장용
        int min = Integer.MAX_VALUE;

        // 정답 도시 저장용
        List<Integer> answer = new ArrayList<>();

        // 친구들의 도시의 숫자
        int C = Integer.parseInt(br.readLine());
        int[] select = new int[C];

        // 친구들의 도시를 입력받는다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            select[i] = Integer.parseInt(st.nextToken());
        }

        // 모든 도시에 대해서, 시작도시 - 도착도시 -> 도착도시 - 시작도시의 거리를 입력받는다.
        loop:
        for (int i = 1; i <= N; i++) {
            int cnt = Integer.MIN_VALUE/2;
            for (int j = 0; j < C; j++) {
                // 왕복 최대 시간 구하기
                cnt = Math.max(cnt, map[select[j]][i] + map[i][select[j]]);
            }
            // min과 비교하여 cnt가 더 작으면 list를 지워주고 새로운 원소를 넣는다.
            if (min > cnt) {
                min = cnt;
                answer.clear();
                answer.add(i);
            }
            // 같은 경우엔 list에 도시를 추가한다.
            else if (min == cnt) {
                answer.add(i);
            }
        }

        // list에 저장된 값 출력
        StringBuilder sb = new StringBuilder();
        for (int a : answer) {
            sb.append(a).append(" ");
        }
        System.out.println(sb);
    }
}