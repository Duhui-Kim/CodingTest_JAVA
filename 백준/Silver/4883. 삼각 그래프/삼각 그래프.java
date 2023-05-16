import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        int testCase = 1;

        StringBuilder sb = new StringBuilder();
        while(true) {
            int N = Integer.parseInt(br.readLine());

            if (N == 0) break;

            int[][] arr = new int[N][3];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                arr[i][2] = Integer.parseInt(st.nextToken());
            }

            for(int j = 0; j < 3; j++) {
                if(j==0) arr[1][0] += arr[0][1];
                else {
                    arr[1][j] += Math.min(arr[1][j-1], Math.min(arr[0][1], arr[0][1]+arr[0][2]));
                }
            }
            
            for(int i = 2; i < N; i++) {
                arr[i][0] += Math.min(arr[i-1][0], arr[i-1][1]);
                arr[i][1] += Math.min(Math.min(arr[i][0], arr[i-1][0]), Math.min(arr[i-1][1], arr[i-1][2]));
                arr[i][2] += Math.min(arr[i][1],Math.min(arr[i-1][1], arr[i-1][2]));
            }
            sb.append(testCase + ". " + arr[N-1][1]).append("\n");

            testCase++;
        }
        System.out.println(sb);
    }

}