import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    // 폴더 구조를 저장할 Directory 객체
    private static class Directory {
        TreeMap<String, Directory> subDir;

        private Directory () {
            subDir = new TreeMap<>();
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // Directory 객체의 root를 만든다.
        Directory root = new Directory();
        int N = Integer.parseInt(br.readLine());

        // 반복문을 진행
        for (int i = 0; i < N; i++) {
            // 각 폴더는 \로 구분되어있음
            StringTokenizer st = new StringTokenizer(br.readLine(), "\\");

            // \가 나올 때마다 아래에 이미 해당 directory가 있다면 그 directory로 이동하고,
            // 없을 시에는 만들어준 뒤 이동한다.
            Directory cur = root;
            while (st.hasMoreTokens()) {
                String input = st.nextToken();
                if(cur.subDir.get(input) == null) {
                    cur.subDir.put(input, new Directory());
                }
                cur = cur.subDir.get(input);
            }
        }

        // 출력을 위한 stringbuilder 선언
        StringBuilder sb = new StringBuilder();
        
        findDepth(sb, 0, root);

        System.out.println(sb);
    }

    private static void findDepth(StringBuilder sb, int k, Directory cur) {

        // treeMap에서 key를 오름차순으로 하나씩 꺼내서 DFS를 진행한다.
        for (String key : cur.subDir.keySet()) {
            for (int i = 0; i < k; i++) sb.append(" ");
            sb.append(key).append("\n");
            findDepth(sb, k+1, cur.subDir.get(key));
        }
    }
}