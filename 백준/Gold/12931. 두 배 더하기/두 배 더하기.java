import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N 입력받음
        int N = Integer.parseInt(br.readLine());

        // N 크기의 배열 생성
        int[] arr = new int[N];

        // 배열에 값 넣어줌
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 연산횟수를 셀 cnt 선언
        int cnt = 0;

        // 모든 원소가 0이 되면 종료
        while (true) {
            int zeroCnt = 0;
            boolean division = false;

            // 홀수인 수가 있으면 1을 빼주고 cnt을 올리고,
            // 전체 짝수들을 2로 나눠줌
            for (int i = 0; i < N; i++) {
                if(arr[i]%2 == 1) {
                    arr[i]--;
                    cnt++;
                }
                if(arr[i] != 0) {
                    arr[i] = arr[i]/2;
                    division = true;
                }
                if(arr[i] == 0) zeroCnt++;
            }
            // 2로 나누는 연산 실행 시 cnt 증가
            if(division) cnt++;

            // 0의 개수가 N개이면 종료
            if(zeroCnt == N) break;
        }

        System.out.println(cnt);
    }
}