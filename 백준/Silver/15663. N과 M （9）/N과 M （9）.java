import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 순서를 지키는 set!!!
    private static LinkedHashSet<String> set = new LinkedHashSet<>();
    private static StringBuilder sb = new StringBuilder();
    private static boolean[] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        check = new boolean[N];

        // arr에 숫자 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] save = new int[M];

        // 사전 순으로 출력해야하므로 정렬
        Arrays.sort(arr);

        // 백트래킹 진행
        backTracking(arr, save, N, M, 0);

        // list에 있는 값을 StringBuilder로 옮김
        for (String s : set) {
            sb.append(s).append("\n");
        }
        // 출력
        System.out.println(sb);
    }

    private static void backTracking(int[] arr, int[] save, int n, int m, int k) {
        if(k == m) {
            // save에 있는 int로 String을 만든다.
            for (int a : save) {
                sb.append(a).append(" ");
            }
            // set에 만들어진 String을 추가한다. (중복 방지)
            set.add(sb.toString());

            // 스트링빌더 초기화
            sb.setLength(0);
            return;
        }

        // 사용한 위치의 숫자는 다시 사용 X
        for (int i = 0; i < n; i++) {
            if(check[i]) continue;
            save[k] = arr[i];
            check[i] = true;
            backTracking(arr, save, n, m, k+1);
            check[i] = false;
        }
    }
}
