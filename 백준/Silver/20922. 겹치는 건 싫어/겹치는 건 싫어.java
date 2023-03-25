import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // map 선언
        HashMap<Integer, Integer> map = new HashMap<>();

        // 인풋 입력받기
        int[] input = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // p는 시작점쪽, q는 끝쪽
        // cnt는 수열 길이 측정
        // maxCnt는 최장 길이 저장
        int p = -1;
        int q = 0;
        int cnt = 0;
        int maxCnt = 0;

        // q가 0 ~ N-1까지 진행
        while (q < N) {
            // map에 해당 값이 없으면 만들어주고 value를 1로 두기
            if(map.get(input[q]) == null) {
                map.put(input[q], 1);
            } 
            // 해당 값이 이미 있으면 value를 1 증가시키기
            else {
                map.replace(input[q], map.get(input[q])+1);
            }
            // 해당 값이 k보다 클 경우 시작점을 증가시키며 K보다 작아질 때까지 cnt를 감소시킴
            while (map.get(input[q]) > K) {
                p++;
                cnt--;
                map.replace(input[p], map.get(input[p])-1);
            }
            // 위의 경우가 완료되면 q와 cnt 증가시킴
            q++;
            cnt++;
            // maxCnt와 비교하기
            maxCnt = Math.max(cnt, maxCnt);
        }
        System.out.println(maxCnt);
    }
}