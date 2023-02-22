import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        // 리스트에 숫자 입력받기
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 투포인터 활용하기
        int sumNum = 0;
        int en = 0;
        int cnt = 0;
        for(int i=0; i<N; i++){
            // 더한 값을 0으로 두고, en는 i부터 시작
            sumNum = 0;
            en = i;
            // en이 N 이하이고 합계가 M보다 작을 때
            // en을 하나씩 늘려가며 합계를 측정함
            while(en < N && sumNum < M) {
                sumNum += arr[en];
                
                // M과 같은 값이 나오면 cnt를 증가시키고 종료
                if(sumNum == M) {
                    cnt++;
                    break;
                }
                en++;
            }
        }
        System.out.println(cnt);
    }
}
