import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // N 크기의 배열 만들기
        int[] arr = new int[N];

        // 배열에 스위치 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 사람 수 받기
        int pNum = Integer.parseInt(br.readLine());

        // 여자일 경우와 남자일 경우에 따라 계산하기
        for (int i = 0; i < pNum; i++) {
            st = new StringTokenizer(br.readLine());
            int people = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            // 남자의 경우 주어진 수의 배수만큼 스위치 상태를 바꾼다.
            if(people == 1){
                int tmp = num - 1;
                while(tmp < N){
                    arr[tmp] = (arr[tmp] + 1)%2;
                    tmp += num;
                }

            // 여자의 경우 해당 스위치를 중심으로 상태가 같은 스위치를 바꾼다.
            } else {
                int a = num - 1;
                int b = num - 1;
                while(a >= 0 && b < N){
                    if(arr[a] == arr[b]) {
                        a--;
                        b++;
                    } else {
                        break;
                    }
                }
                for(int j=a+1; j<b; j++){
                    arr[j] = (arr[j] + 1)%2;
                }
            }
        }
        int cnt = 0;
        for(int k : arr){
            sb.append(k + " ");
            if(cnt%20 == 19) sb.append("\n");
            cnt++;
        }
        System.out.println(sb);
    }
}
