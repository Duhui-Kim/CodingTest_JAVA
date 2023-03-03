package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 개수
        int N = Integer.parseInt(br.readLine());

        // 봉우리 쌍을 저장할 list 배열
        ArrayList<long[]> list = new ArrayList<>();

        // 입력을 저장할 long 배열
        long[][] input = new long[N][2];

        // 입력 모두 저장
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }

        // 이전의 x값, y값과 현재의 x값, y값을 비교할건데,
        // 첫 번째 x값과 y값의 이전 x, y값은 맨 마지막으로 입력된 값
        long prevX = input[N-1][0];
        long prevY = input[N-1][1];

        // cnt = 쌍을 표현
        int cnt = 1;

        // N번 반복하는데,
        for (int i = 0; i < N; i++) {
            long x = input[i][0];
            long y = input[i][1];

            // x 좌표가 일치하고 y좌표가 마이너스에서 플러스로 올라갈 때 봉우리의 올라가는 지점으로 list에 저장
            if(prevX == x && y > 0 && prevY < 0) {
                long[] arr = new long[3];
                arr[0] = cnt;
                arr[1] = x;
                arr[2] = 0;
                list.add(arr);

            // x 좌표가 일치하고 y좌표가 플러스에서 마이너스로 내려올 때 봉우리의 끝점이므로 list에 저장하고 cnt를 1 올려줌
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

        // 만약 입력이 내려가는 것부터 시작하면 마지막에 올라오는 것은 1쌍이 안채워지므로 1로 바꿔줌
        if(list.get(list.size()-1)[0] != list.get(list.size()-2)[0]) {
            list.get(list.size()-1)[0] = 1;
        }

        // x 좌표 순으로 정렬
        Collections.sort(list, ((o1, o2) -> {
            return Long.compare(o1[1], o2[1]);
        }));

        // 결과 체크를 위한 stack 생성
        ArrayDeque<long[]> stack = new ArrayDeque<>();

        int independent = 0;
        int notInclude = 0;

        // list의 size만큼 반복 진행
        for (int i = 0; i < list.size(); i++) {
            // stack이 비어있을 때
            //  1. list 값을 stack에 넣어주기
            if(stack.isEmpty()) stack.offerLast(list.get(i));

            // stack이 비어있지 않고 stack의 맨 위의 쌍 번호가 list의 쌍 번호와 같을 때
            //  1. stack 포함여부(2번idx의 값)가 0이면 notInclude ++
            //  2. stack의 값을 하나 뺐으므로 stack이 비어있다면 포함되지 않았으므로 independent++ 
            else if (stack.peekLast()[0] == list.get(i)[0]) {
                if(stack.pollLast()[2] == 0) notInclude++;
                if(stack.isEmpty()) independent++;
                
            // 그 외의 경우는 stack이 비어있지 않고 stack 맨 위의 쌍 번호가 list의 쌍 번호와 다를 경우이다.
            //  1. stack에 있던 배열의 2번 idx를 1로 바꿔주고, list 값을 stack에 넣어줌
            } else {
                stack.peekLast()[2] = 1;
                stack.offerLast(list.get(i));
            }
        }
        // 결과 출력
        System.out.println(independent + " " + notInclude);
    }
}
