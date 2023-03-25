import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력숫자의 크기 N 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // N+1 크기의 배열 선언
        int[] arr = new int[N+1];

        // 숫자 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long answer = 0;

        long p = 0;
        long q = 0;

        // 숫자 체크용 배열
        int[] check = new int[100001];

        int cnt = 0;

        while (q < N) {
            check[arr[(int)q]]++;

            // 겹치는 수가 있다면 없어질 때까지 왼쪽 포인터를 이동시키며 값을 빼준다
            while (p < q && check[arr[(int)q]] > 1) {
                answer += q - p;
                check[arr[(int)p]]--;
                p++;
            }
            q++;
        }

        // while문이 종료되었으면 q가 N에 도달한 것이므로
        // p부터 끝까지는 겹치는 수가 없음. 따라서 남은 cnt를 더해줌
        answer += ((q-p) * (q-p+1)) / 2;

        System.out.println(answer);
    }
}