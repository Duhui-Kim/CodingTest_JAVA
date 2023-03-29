import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 지역의 개수 N, 수색범위 M, 길의 개수 R
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        // 구역별 아이템을 저장할 배열
        int[] items = new int[N+1];

        // 각 구역별 아이템 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            items[i] = Integer.parseInt(st.nextToken());
        }

        // N+1 크기의 2차 배열 생성
        int[][] map = new int[N+1][N+1];

        // 지역과 거리 입력받기
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            map[a][b] = l;
            map[b][a] = l;
        }

        // 연결되어있지 않다면 1000을 넣어둔다.
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;

                if (map[i][j] == 0) map[i][j] = 1000;
            }
        }

        // 바로 가는 거리와 다른 경로를 거쳐가는 거리 중 최단거리 저장하기
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if(i == j) continue;

                    if(map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        // 최대 아이템 개수 구하기
        int maxItems = 0;
        
        // 반복문을 돌며 한 지점에서 출발해서 M 이하인 지점이 있는지 체크해서 item을 줍는다.
        for (int i = 1; i <= N; i++) {
            int itemCnt = 0;
            for (int j = 1; j <= N; j++) {
                if (map[i][j] <= M) itemCnt += items[j];
            }
            // 해당 지점에서 주운 아이템과 맥스를 비교하여 큰 값을 저장
            if (maxItems < itemCnt) {
                maxItems = itemCnt;
            }
        }
        System.out.println(maxItems);
    }
}