import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main {
    private static int[][] field;
    private static Queue<int[]> queue = new ArrayDeque<>();
    private static int testCase;
    private static int n;
    private static int m;
    private static int k;
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));


        testCase = Integer.parseInt(br.readLine());

        // 테스트케이스만큼 진행
        for(int l=0; l<testCase; l++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // map 입력받기
            field = new int[n][m];
            for(int i=0; i<k; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                field[x][y] = 1;
            }

            // 지렁이 수 체크용
            int warm = 0;

            // n, m만큼 반복을 진행
            for(int i=0; i<n; i++) {
                for(int j=0; j<m; j++) {
                    // 배추를 만나면 BFS 진행
                    if(field[i][j] == 1) {
                        // 지렁이 수를 일단 증가시키고, 해당 지역의 배추를 없앰.
                        warm++;
                        field[i][j] = 0;
                        int[] array = new int[2];
                        array[0] = i;
                        array[1] = j;
                        queue.offer(array);

                        // queue가 빌 때까지 반복
                        while(!queue.isEmpty()) {
                            // queue에서 좌표를 꺼내서 nx, ny에 각각 넣어준다.
                            int[] arr = queue.poll();
                            int nx = arr[0];
                            int ny = arr[1];
                            
                            // 사방탐색 진행
                            for(int k=0; k<4; k++) {
                                int cx = nx + dx[k];
                                int cy = ny + dy[k];
                                // 범위 밖이면 지나간다
                                if(cx<0 || cy<0 || cx>=n || cy>=m) continue;
                                // 배추를 만나면 배추를 0으로 만들어주고 해당 좌표를 또 queue에 넣어준다.
                                if(field[cx][cy] == 1) {
                                    field[cx][cy] = 0;
                                    int[] newArray = new int[2];
                                    newArray[0] = cx;
                                    newArray[1] = cy;
                                    queue.offer(newArray);
                                }
                            }
                        }
                    }
                }
            }
            // 모든 좌표에서 BFS가 종료되면 끝.
            // 지렁이의 수 = BFS가 진행된 횟수
            bw.write(warm + "\n");
        }
        bw.close();
    }
}