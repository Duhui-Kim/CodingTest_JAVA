import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= testCase; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            // A의 길이 N과 M의 길이 M을 입력받고 배열 생성
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            int[] B = new int[M];

            // A와 B를 입력받는다.
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }
            
            // A와 B를 정렬하고 시작
            Arrays.sort(A);
            Arrays.sort(B);

            // 결과를 저장할 sumCnt, 먹는 쌍이 늘어날떄마다 올려줄 cnt
            // a의 idx와 b의 idx
            int sumCnt = 0;
            int cnt = 0;
            int aIdx = 0;
            int bIdx = 0;

            // aIdx가 N에 도달하면 종료
            while (aIdx < N) {
                // bIdx가 M에 도달하면, 그 이후의 A들은 B를 모두 먹을 수 있으므로 sumCnt에 cnt를 더해줌
                if(bIdx >= M) {
                    sumCnt += cnt;
                    aIdx++;
                    continue;
                }
                // A가 B보다 크면 cnt를 올려주고 b의 다음 생명체로 간다
                if(A[aIdx] > B[bIdx]) {
                    bIdx++;
                    cnt++;
                } 
                // A가 B보다 작거나 같으면 저장해둔 cnt를 sumCnt에 옮기고 다음 a 생명체를 부른다. 
                else {
                    sumCnt += cnt;
                    aIdx++;
                }
            }
            sb.append(sumCnt).append("\n");
        }
        System.out.println(sb);
    }
}