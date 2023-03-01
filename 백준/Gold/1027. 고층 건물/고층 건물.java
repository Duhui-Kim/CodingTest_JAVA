import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        double[] building = new double[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            building[i] = Double.parseDouble(st.nextToken());
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            max = Math.max(max, checkBuilding(building, i, N));
        }
        System.out.println(max);
    }
    private static int checkBuilding(double[] building, int idx, int N) {
        double midHeight = 0;
        int left = -1;
        int midIdx = idx - 1;

        int cnt = 0;

        for (int i = 1; i < N; i++) {
            if(idx + left * i < 0 || idx + left * i >= N) {
                break;
            }
            double compare = building[idx] - Math.abs(left * i) * (building[idx] - midHeight) / Math.abs(idx - midIdx);
            if(building[idx + left * i] > compare) {
                midHeight = building[idx + left * i];
                midIdx = idx + left * i;
                cnt++;
            }
        }

        midHeight = 0;
        int right = 1;
        midIdx = idx - 1;

        for (int i = 1; i < N; i++) {
            if(idx + right * i < 0 || idx + right * i >= N) {
                break;
            }
            double compare = building[idx] - Math.abs(right * i) * (building[idx] - midHeight) / Math.abs(idx - midIdx);
            if(building[idx + right * i] > compare) {
                midHeight = building[idx + right * i];
                midIdx = idx + right * i;
                cnt++;
            }
        }
        return cnt;
    }
}