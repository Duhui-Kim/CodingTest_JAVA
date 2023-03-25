import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb;
    private static int N;
    private static int M;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] input = new int[N];

        st = new StringTokenizer(br.readLine());

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        N = set.size();

        arr = new int[N];

        int idx = 0;
        for(int a : set) {
            arr[idx++] = a;
        }

        Arrays.sort(arr);

        sb = new StringBuilder();

        int[] answer = new int[M];

        backTracking(answer, 0);

        System.out.println(sb);
    }

    private static void backTracking(int[] answer, int k) {
        if(k == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            answer[k] = arr[i];
            backTracking(answer, k+1);
        }
    }
}