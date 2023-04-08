import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static class Pair {
        int num;
        int idx;
        private Pair(int num, int idx) {
            this.num = num;
            this.idx = idx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 우주의 개수 M과 행성의 개수 N
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 행성 담을 배열
        ArrayList<Pair>[] arr = new ArrayList[M];

        // 행성을 입력받으면서 idx를 함께 묶어주고 크기 순으로 정렬
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new ArrayList<>();

            for (int j = 1; j <= N; j++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i].add(new Pair(num, j));
            }
            Collections.sort(arr[i], ((o1, o2) -> o1.num - o2.num));

            // 크기가 같을 때 idx 같게 만들기
            for (int j = 1; j < N; j++) {
                if (arr[i].get(j).num == arr[i].get(j-1).num) {
                    arr[i].get(j).idx = arr[i].get(j-1).idx;
                }
            }
        }

        // 정렬된 상태 담을 string 배열
        String[] ans = new String[M];

        StringBuilder sb;
        
        // 각 행성을 돌며 idx를 String 형태로 저장
        for (int i = 0; i < M; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < N; j++) {
                sb.append(arr[i].get(j).idx);
            }
            ans[i] = sb.toString();
            sb.setLength(0);
        }
        // 배열 정렬
        Arrays.sort(ans);
        
        int cnt = 0;
        
        // 배열을 순회하며 String이 같을 경우 cnt 올림.
        // 정렬되어있으므로 같았다가 달라지면 뒤쪽은 안봐도 되므로 다음으로 넘어감
        loop:
        for (int i = 0; i < M-1; i++) {
            boolean same = false;
            for (int j = i+1; j < M; j++) {
                if (ans[i].equals(ans[j])) {
                    cnt++;
                    same = true;
                } else if (same) {
                    continue loop;
                }
            }
        }
        System.out.println(cnt);
    }
}