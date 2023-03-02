import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        
        // 테스트케이스만큼 반복 진행
        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            // 명령어 개수를 입력받음
            int cNum = sc.nextInt();

            // 명령어 개수 + 1 크기만큼의 배열을 만들고, lastIdx를 0으로 설정
            int[] heap = new int[cNum+1];
            int lastIdx = 0;

            sb.append("#" + t);

            for (int i = 0; i < cNum; i++) {
                int command = sc.nextInt();

                // 입력 명령이면 숫자를 입력받아야함
                if(command == 1) {

                    // 마지막 idx에 값을 넣는다.
                    heap[++lastIdx] = sc.nextInt();

                    // 해당 숫자부터 root까지 heap 정렬 시작
                    int cur = lastIdx;
                    while (cur > 1 && heap[cur] > heap[cur/2]) {
                        swap(heap, cur, cur/2);
                        cur /= 2;
                    }

                // 출력 명령은 따로 입력받는 것 없이 출력 진행
                } else {
                    // lastIdx가 0이면 비어있다는 것이므로 -1 출력
                    if(lastIdx == 0) {
                        sb.append(" " + -1);
                        continue;
                    }

                    // 숫자가 존재한다면, 1번 idx값을 출력하고 맨 뒤의 값을 1번 idx로 가져옴
                    sb.append(" " + heap[1]);
                    heap[1] = heap[lastIdx--];

                    // root부터 leaf까지 heap 정렬 시작
                    int cur = 1;
                    while (true) {
                        // child를 cur * 2로 바꿈
                        int child = cur * 2;

                        // child+1이 lastIdx 안쪽에 있을 때 왼쪽 자식과 오른쪽 자식의 크기를 비교
                        // 오른쪽 자식이 더 크면 child에 +1을 해줌
                        if(child + 1 <= lastIdx && heap[child] < heap[child+1]) {
                            child += 1;
                        }

                        // child가 lastIdx 안쪽이고 cur와 child를 비교했을 때 자식노드가 더 크면
                        // 두 수를 바꿔주고 cur를 child로 바꿈
                        if(child <= lastIdx && heap[cur] < heap[child]) {
                            swap(heap, cur, child);
                            cur = child;

                        // child가 범위를 벗어났거나 부모노드가 더 크면 종료
                        } else {
                            break;
                        }
                    }
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void swap(int[] heap, int i, int j) {
        int tmp = heap[j];
        heap[j] = heap[i];
        heap[i] = tmp;
    }
}