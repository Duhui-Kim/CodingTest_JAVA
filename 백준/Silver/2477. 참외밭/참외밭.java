import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[6][2];
        int[] dir = new int[5];

        for (int i = 0; i < 6; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());

            dir[arr[i][0]]++;
        }

        int bigSqure = 1;
        int smallSqure = 1;
        for (int i = 0; i < 5; i++) {
            if(dir[i] == 1){
                for(int j=0; j<6; j++){
                    if(arr[j][0] == i){
                        bigSqure *= arr[j][1];
                        smallSqure *= arr[(j+3)%6][1];
                    }
                }
            }
        }
        System.out.println(N * (bigSqure - smallSqure));
    }
}
