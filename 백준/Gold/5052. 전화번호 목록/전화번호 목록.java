import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Main {

    // 트라이 객체 선언
    private static class Trie {
        HashMap<Character, Trie> children = new HashMap<>();
        boolean idEnd;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 테스트 케이스만큼 반복 진행
        int testCase = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < testCase; tc++) {
            // root를 선언한다.
            Trie root = new Trie();

            int N = Integer.parseInt(br.readLine());

            // N개의 번호 중 하나라도 일관성이 없다면 체크는 false가 된다.
            boolean check = true;
            for (int i = 0; i < N; i++) {
                char[] input = br.readLine().toCharArray();
                
                // 이미 일관성이 깨졌다면 아래 진행 안해도 됨
                if (!check) continue;
                
                if (!insert(root, input)) {
                    check = false;
                }
            }
            // check가 true이면 일관성 있는 것이고 아닌 경우는 없는 것
            if (check) sb.append("YES\n");
            else sb.append("NO\n");
        }
        System.out.println(sb);
    }

    private static boolean insert(Trie root, char[] input) {
        // root를 시작점으로 잡고 시작
        Trie cur = root;

        // input의 문자를 하나씩 꺼내서 진행
        for(char c : input) {
            // 자식노드가 없다면 추가해줌
            if (cur.children.get(c) == null) {
                cur.children.put(c, new Trie());
            }
            // 현재 노드를 자식 노드의 위치로 이동
            cur = cur.children.get(c);

            // isEnd가 true일 경우 이전에 입력한 숫자의 끝이므로 일관성 X
            if (cur.idEnd) {
                return false;
            }
        }
        // 끝까지 내려왔는데 자식노드가 있다면 일관성 X
        if (cur.children.size() != 0) return false;

        // 위의 경우가 아니라면 체크해주고 true 반환
        cur.idEnd = true;
        return true;
    }
}