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
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        // set에 입력받기(중복되는 수 알아서 제거)
        HashSet<Integer> set = new HashSet<>();

        // N개의 숫자를 set에 입력받는다.
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }

        // N을 set의 크기로 바꿔주고
        N = set.size();

        // arr를 만들어준다.
        arr = new int[N];
        int[] answer = new int[M];

        // set에 있는 숫자를 꺼내서 arr에 넣어준다.
        int idx = 0;
        for(int a : set) {
            arr[idx++] = a;
        }
        // 정렬해준 뒤 백트래킹 시작
        Arrays.sort(arr);

        // 이전에 넣었던 값까지 전달
        backTracking(answer, 0, 0);

        System.out.println(sb);
    }

    private static void backTracking(int[] answer, int idx, int k) {
        // k가 M에 도달하면 answer에 있는 원소 출력하기
        if(k == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        // arr에 있는 원소에 대해서 모든 경우의 수 골라주기
        // 이전에 넣었던 값을 포함한 이후의 idx에서만 고르기
        for (int i = idx; i < N; i++) {
            answer[k] = arr[i];
            backTracking(answer, i, k+1);
        }
    }
}