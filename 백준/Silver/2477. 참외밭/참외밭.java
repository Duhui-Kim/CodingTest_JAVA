import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1m2당 참외의 수를 입력받는다.
        int N = Integer.parseInt(br.readLine());

        // 입력을 저장할 arr을 선언하고, 각 direction 중 한 번 나온 값을 체크하기 위해 dir을 만든다.
        int[][] arr = new int[6][2];
        boolean[] dir = new boolean[5];

        for (int i = 0; i < 6; i++) {
            // 방향을 0번 idx에 저장하고 길이를 1번 idx에 저장한다.
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            // 1번 나오면 true / 2번 나오면 false가 됨
            dir[arr[i][0]] = !dir[arr[i][0]];
        }
        
        // 큰 사각형과 작은 사각형의 넓이를 저장할 인자를 선언한다.
        int bigSqure = 1;
        int smallSqure = 1;
        
        /*
         * i는 1부터 4까지 돌며 dir이 true인 지점을 찾았을 때 그 i가 큰 변의 길이이므로
         * arr[j][0]이 큰 변과 같은 지점을 찾아서 큰 사각형의 넓이에 1번 idx를 곱해준다.
         * arr[(j+3)%6][1]은 작은 사각형의 넓이에 곱해준다.
         */
        for (int i = 1; i < 5; i++) {
            if(dir[i]){
                for(int j=0; j<6; j++){
                    if(arr[j][0] == i){
                        bigSqure *= arr[j][1];
                        smallSqure *= arr[(j+3)%6][1];
                    }
                }
            }
        }
        // 1m2 당 참외의 수에 사각형의 넓이를 곱해준다.
        System.out.println(N * (bigSqure - smallSqure));
    }
}
