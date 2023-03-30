import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        // 나중에 바꿔주기 10으로
        for (int tc = 1; tc <= 10; tc++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());
            int N = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());

            // 인접리스트로 풀어보기
            ArrayList<Integer>[] list = new ArrayList[101];
            for (int i = 0; i < 101; i++) {
                list[i] = new ArrayList<>();
            }

            // 이미 연락했는지 확인용
            boolean[] check = new boolean[101];
            check[start] = true;

            // 입력을 받아서 해당 idx의 배열에 도착점 넣기
            st = new StringTokenizer(sc.nextLine());
            for (int i = 0; i < N/2; i++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                list[a].add(b);
            }

            // 시작지점 넣기
            Queue<Integer> queue = new LinkedList<>();

            queue.offer(start);

            // max 값을 저장하기 위해 밖에 선언
            int max = 0;

            // queue가 빌 때까지 진행
            while (!queue.isEmpty()) {
                // 한 cycle마다 max를 0으로 초기화해둠
                max = 0;
                int step = queue.size();

                // queue의 size만큼씩만 진행 (1 cycle)
                for (int i = 0; i < step; i++) {
                    // queue에서 뽑아서 max와 비교 후 제일 큰 값을 넣어준다.
                    int x = queue.poll();
                    if(max < x) max = x;

                    for (int a : list[x]) {
                        if(check[a]) continue;
                        check[a] = true;
                        queue.offer(a);
                    }
                }
                // 여기서 queue가 비어있다면,
                // 마지막 연락까지 돌린 것이므로 max를 가진채로 종료
            }
            // 정답 저장
            sb.append(String.format("#%d %d\n", tc, max));
        }
        // 출력
        System.out.println(sb);
    }
}