import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {
            // 명령어 개수를 입력받고 speed와 distance를 0으로 초기화한다.
            int command = sc.nextInt();
            int speed = 0;
            int distance = 0;

            for (int i = 0; i < command; i++) {
                // 명령어를 입력받는데 만약 명령어가 0이라면 추가적인 숫자가 없으므로 accel 값을 입력받지 않는다.
                int input = sc.nextInt();
                int accel = 0;
                if(input != 0) {
                    accel = sc.nextInt();
                }
                // 명령어가 0, 1, 2 중 어떤 것이냐에 따라 수행한다.
                // 감속 명령어의 경우 speed가 0보다 작아지면 0으로 초기화한다.
                if(input == 0) {
                    distance += speed;
                } else if (input == 1) {
                    speed += accel;
                    distance += speed;
                } else {
                    speed -= accel;
                    if(speed < 0) speed = 0;
                    distance += speed;
                }
            }
            System.out.printf("#%d %d\n", t, distance);
        }
    }
}
