import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int Root = 1;
    private static int unused = 2;
    private static int MX = 10000 * 500 + 5;
    private static int[][] Trie = new int[MX][26];
    private static boolean[] check = new boolean[MX];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // -1로 초기화
        for (int i = 0; i < MX; i++) {
            Arrays.fill(Trie[i], -1);
        }
        // 문자열 입력받기
        for (int i = 0; i < N; i++) {
            char[] input = br.readLine().toCharArray();
            insert(input);
        }

        // 일치하는 문자열 있는지 찾기
        int answer = 0;
        for (int i = 0; i < M; i++) {
            char[] input = br.readLine().toCharArray();
            if (find(input)) answer++;
        }
        // 정답 출력
        System.out.println(answer);
    }

    // 문자열 삽입
    private static void insert(char[] input) {
        int cur = Root;

        // 간선 번호 매기기
        for(char c : input) {
            if (Trie[cur][ctoi(c)] == -1)
                Trie[cur][ctoi(c)] = unused++;
            cur = Trie[cur][ctoi(c)];
        }
        // 맨 끝에 도달하면 끝이라는 표시하기
        check[cur] = true;
    }

    // 문자열 찾기
    private static boolean find(char[] input) {
        int cur = Root;
        // 문자열을 입력받고 맨 끝까지 내려가는데,
        // 내려가다가 사용하지 않은 문자를 만나면 false 반환
        for(char c : input) {
            if (Trie[cur][ctoi(c)] == -1)
                return false;
            cur = Trie[cur][ctoi(c)];
        }
        // 끝까지 내려갔는데, 끝이라는 표시가 안되어있다면 false 반환
        return check[cur];
    }

    // 문자열 삭제
    private static void delete(char[] input) {
        int cur = Root;
        // 문자열의 끝까지 내려간다.
        for(char c : input) {
            if (Trie[cur][ctoi(c)] == -1)
                return;
            cur = Trie[cur][ctoi(c)];
        }
        // 끝까지 내려가서, 끝이라는 표시 지우기
        check[cur] = false;
    }

    // 문자 -> 숫자로 변경
    private static int ctoi(char c) {
        return c - 'a';
    }
}