import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 10개의 testCase를 진행한다.
        for (int t = 1; t <= 10; t++) {

            // 삽입이 많이 일어나므로 LinkedList로 구현했다.
            LinkedList<String> list = new LinkedList<>();

            // N을 입력받고 N개의 숫자(문자열형태로 받음)를 list에 넣어준다.
            int N = sc.nextInt();
            for (int i = 0; i < N; i++) {
                list.offer(sc.next());
            }

            // K를 입력받고, 명령어에 따라서 다음을 수행한다.
            int K = sc.nextInt();
            for (int i = 0; i < K; i++) {
                if (sc.next().equals("I")) {
                    // x의 위치에 y개의 숫자를 삽입
                    // 그냥 x의 idx에 삽입할 경우, 입력하면서 기존에 입력된 숫자가 뒤로 밀려나
                    // 원하는 것과 반대로 숫자가 입력된다. 따라서 x+j에 값을 삽입해준다.
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    for (int j = 0; j < y; j++) {
                        list.add(x + j, sc.next());
                    }
                }
            }
            // t를 출력하고, list에 있는 10개의 숫자를 출력한다.
            System.out.print("#" + t + " ");
            for (int i = 0; i < 10; i++) {
                System.out.print(list.get(i) + " ");
            }
            System.out.println();
        }
    }
}
