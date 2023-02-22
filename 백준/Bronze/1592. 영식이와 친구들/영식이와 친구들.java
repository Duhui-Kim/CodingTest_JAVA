import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 숫자 N, M, K를 입력받는다.
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        br.close();

        // N 크기의 배열을 만들어준다.
        int[] arr = new int[N];
        
        // 공 던진 횟수를 셀 ball과 idx를 계산하기 위해 idx를 선언한다.
        int ball = 0;
        int idx = 0;
        while(true){
            // 처음 시작점인 0번째에 1을 더해준다.
            arr[idx]++;
            // M과 같은 값이 있으면 반복을 종료한다.
            if(arr[idx] == M) break;
            
            // 홀수번째이면 시계방향으로, 짝수번째이면 반시계방향으로 던진다.
            if(arr[idx]%2 == 1){
                idx = (idx + L) % N;
            } else {
                idx = idx - L;
                while(idx < 0){
                    idx += N;
                }
            }
            ball++;
        }
        // 던진 횟수를 출력한다.
        System.out.println(ball);
    }
}
