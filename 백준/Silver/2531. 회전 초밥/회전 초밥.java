import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] dishes = new int[N];
        int[] sushi = new int[d+1];

        // 쿠폰 초밥 추가했으므로 cnt는 1부터 시작
        int cnt = 1;
        sushi[c]++;

        // 초밥벨트 입력받기
        for (int i = 0; i < N; i++) {
            dishes[i] = Integer.parseInt(br.readLine());
        }

        // 0부터 k까지 초기값 입력하기
        for (int i = 0; i < k; i++) {
            // sushi 값이 0이면 새로운 종류의 초밥이므로 cnt 증가
            if(sushi[dishes[i]] == 0) cnt++;
            sushi[dishes[i]]++;
        }
        // 초기값으로 maxCnt 초기화해두고 시작
        int maxCnt = cnt;

        // 시작점을 0와 k에 두고 시작
        int p = 0;
        int q = k;

        // p는 N-1까지만 진행하면 모든 경우가 나옴
        while (p < N) {
            // 맨 앞의 초밥을 빼줬을 때 값이 0이면 종류 1가지 사라짐
            sushi[dishes[p]]--;
            if (sushi[dishes[p]] == 0) cnt--;
            p++;

            // 맨 뒤의 더해줄 초밥의 값이 0이면 종류 1가지 늘어남
            if (sushi[dishes[q%N]] == 0) cnt++;
            sushi[dishes[q%N]]++;
            q++;

            maxCnt = Math.max(cnt, maxCnt);
        }
        System.out.println(maxCnt);
    }
}