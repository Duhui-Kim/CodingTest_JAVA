import java.util.LinkedList;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = 10;

        for (int t = 1; t <= testCase; t++) {
            // 삽입 삭제가 많이 일어나므로 LinkedList 사용
            LinkedList<Integer> list = new LinkedList<>();

            StringBuilder sb = new StringBuilder();

            // list에 값을 입력한다.
            int N = sc.nextInt();
            for (int i = 0; i < N; i++) {
                list.add(sc.nextInt());
            }

            // 명령어를 차례로 입력받으면서 명령을 실행한다.
            int count = sc.nextInt();

            for (int i = 0; i < count; i++) {
                // 삽입
                String command = sc.next();
                if (command.equals("I")) {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    for (int j = 0; j < y; j++) {
                        list.add(x + j, sc.nextInt());
                    }
                }
                // 삭제
                else if (command.equals("D")) {
                    int x = sc.nextInt();
                    int y = sc.nextInt();
                    for (int j = 0; j < y; j++) {
                        list.remove(x);
                    }
                }
                // 추가
                else {
                    int y = sc.nextInt();
                    for (int j = 0; j < y; j++) {
                        list.add(sc.nextInt());
                    }
                }

            }
            sb.append("#" + t + " ");
            for (int i = 0; i < 10; i++) {
                sb.append(list.get(i) + " ");
            }
            System.out.println(sb);
        }
    }
}
