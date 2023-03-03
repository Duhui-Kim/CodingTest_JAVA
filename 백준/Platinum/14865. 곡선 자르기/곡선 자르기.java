import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 봉우리가 만들어지는 조건
        // 1. x좌표가 일치할 때 y좌표가 -에서 +로 올라올 때의 x가 봉우리의 시작점
        // 2. x좌표가 일치할 때 y좌표가 +에서 -로 내려갈 때의 x가 봉우리의 끝점

        // 끝점이 먼저 주어질 수도 있는데 그럴 경우 끝점의 쌍이 없으므로 끝점 쌍의 번호를 1로 바꿔줌

        // 봉우리를 쌍으로 표시하기
        // {1, 시작 x좌표}, {2, 시작 x좌표}, {2, 끝 x좌표}, {3, 시작 x좌표}, {3, 끝 x좌표}, {1, 끝 x좌표}
        // 이런식으로 0번 idx는 쌍의 표현, 1번 idx는 x좌표

        // list를 x좌표 기준으로 정렬

        // 반복문 list.size()만큼 진행
        // 1. stack 비어있으면 list.get(i)를 stack에 넣기

        // 2. stack이 비어있지 않으면
        //  2-1. stack.peek()[0] == list.get(i)[0] 이면 한 쌍 (0번 idx가 쌍을 표현, 1번 idx는 좌표, 2번 idx는 포함여부 체크용)
        //     a. if stack.peek()[2]를 체크 -> 포함하지 않는 봉우리이면 포함하지 않는 봉우리 cnt ++;
        //     b. stack.poll()을 하고 stack이 비어있다면 포함되지 않는 봉우리이므로 cnt++;

        //  2-2. stack.peek()[0] != list.get(i)[0] 이면 다른 쌍이 들어온 것
        //       stack.peek()[2]를 1로 바꿔주고 stack에 list.get(i)를 넣어줌
        //       (각 쌍을 열린괄호 닫힌괄호라고 하면 괄호가 열려있을 때 새로운 열린괄호가 들어온 것이므로 기존에 열려있던 괄호는 포함하는 봉우리가 됨.)


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<long[]> list = new ArrayList<>();



        long[][] input = new long[N][2];

        // 입력 모두 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        long prevX = input[N-1][0];
        long prevY = input[N-1][1];

        int cnt = 1;
        for (int i = 0; i < N; i++) {
            long x = input[i][0];
            long y = input[i][1];

            // 아래서 위로 올라감
            if(prevX == x && y > 0 && prevY < 0) {
                long[] arr = new long[3];
                arr[0] = cnt;
                arr[1] = x;
                arr[2] = 0;
                list.add(arr);

                // 위에서 아래로 내려옴
            } else if (prevX == x && y < 0 && prevY > 0) {
                long[] arr = new long[3];
                arr[0] = cnt;
                arr[1] = x;
                arr[2] = 0;
                list.add(arr);

                cnt++;
            }
            prevX = x;
            prevY = y;

        }

        // 내려가는 것부터 시작하면 마지막에 올라오는 것은 1쌍이 안채워지므로 1로 바꿔줌
        if(list.get(list.size()-1)[0] != list.get(list.size()-2)[0]) {
            list.get(list.size()-1)[0] = 1;
        }

        // 크기대로 정렬
        Collections.sort(list, ((o1, o2) -> {
            return Long.compare(o1[1], o2[1]);
        }));

        ArrayDeque<long[]> stack = new ArrayDeque<>();

        int independent = 0;
        int notInclude = 0;


        for (int i = 0; i < list.size(); i++) {
            if(stack.isEmpty()) stack.offerLast(list.get(i));
            else if (stack.peekLast()[0] == list.get(i)[0]) {
                if(stack.pollLast()[2] == 0) notInclude++;
                if(stack.isEmpty()) independent++;
            } else {
                stack.peekLast()[2] = 1;
                stack.offerLast(list.get(i));
            }
        }
        System.out.println(independent + " " + notInclude);
    }
}