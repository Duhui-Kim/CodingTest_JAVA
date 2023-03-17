import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<String> vowel = new ArrayList<>(Arrays.asList("a", "e", "i", "o", "u"));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 숫자 -> 문자의 개수와 고를 개수
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 모음의 개수
        int V = 0;

        // 자음의 개수
        int C = 0;

        // 인풋 받을 배열과 정답 저장할 배열
        String[] arr = new String[N];
        String[] ans = new String[M];

        // 알파벳 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = st.nextToken();
        }

        // 사전 순으로 정렬하기
        Arrays.sort(arr);

        /*
         백트래킹
         1. 인풋 데이터
         2. 정답 배열
         3. 반복횟수
         4. 모음 개수
         5. 자음 개수
         */
        backTracking(arr, ans, M, V, C, -1);

        // 정답 출력
        System.out.println(sb.toString());
    }

    private static void backTracking(String[] arr, String[] ans, int m, int v, int c, int x) {
        if(v + c == m) {
            // 자음과 모음이 최소 개수보다 작으면 리턴
            if (v < 1 || c < 2) return;

            // 정답 배열에서 문자를 꺼내서 stringBuilder에 넣기
            for(String s : ans) {
                sb.append(s);
            }
            sb.append("\n");
            return;
        }

        // 반복을 진행하며, 이전에 넣었던 문자는 넣지 않고
        // 새로운 문자 중에서 자음이면 자음 cnt 증가, 모음이면 모음 cnt 증가
        for (int i = x+1; i < arr.length; i++) {
            if (vowel.contains(arr[i])) {
                ans[v+c] = arr[i];
                backTracking(arr, ans, m, v+1, c, i);
            } else {
                ans[v+c] = arr[i];
                backTracking(arr, ans, m, v, c+1, i);
            }
        }
    }
}