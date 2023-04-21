import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int ROOT = 1, unused = 2;
    private static int MX = 10000 * 500 + 5;
    private static int[][] trie = new int[27][MX];
    private static boolean[] check = new boolean[MX];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 문자열 입력받기
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            insert(input);
        }

        // 접두사 찾기
        int answer = 0;
        for (int i = 0; i < M; i++) {
            if (find(br.readLine().toCharArray()))
                answer++;
        }
        System.out.println(answer);
    }

    // 문자를 숫자로 바꾸기
    private static int ctoi(char c) {
        return c-'a'+1;
    }

    // 삽입 연산
    private static void insert(char[] input) {
        int cur = ROOT;
        for(char c : input) {
            if(trie[ctoi(c)][cur] == 0)
                trie[ctoi(c)][cur] = unused++;
            cur = trie[ctoi(c)][cur];
        }
        check[cur] = true;
    }

    // 접두사 확인 연산
    private static boolean find(char[] input) {
        int cur = ROOT;
        for(char c : input) {
            if(trie[ctoi(c)][cur] == 0)
                return false;
            cur = trie[ctoi(c)][cur];
        }
        // 접두사이므로 마지막일 필요가 없음
        return true;
    }
}