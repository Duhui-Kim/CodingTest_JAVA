import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    private static int[] twelve = {0, 0, 0, 0, 0};
    private static int[] three = {0, 2, 2, 2, 2};
    private static int[] nine = {0, 6, 6, 6, 6};
    private static char[][] gear;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 톱니번호와 idx를 일치화하기 위해 5칸으로 선언
        gear = new char[5][8];

        // 굳이 int 형태로 계산하지 않아도 되므로 char 형태로 받았다.
        for (int i = 1; i <= 4; i++) {
            gear[i] = br.readLine().toCharArray();
        }

        // command 숫자를 입력받는다.
        int cNum = Integer.parseInt(br.readLine());

        // 입력받은 숫자대로 command 진행
        for (int i = 0; i < cNum; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int answer = 0;
        int point = 1;
        for (int i = 1; i <= 4; i++) {
            // gear의 12번째가 1이면 포인트를 더해줌
            if(gear[i][twelve[i]] == '1') {
                answer += point;
            }
            // 포인트는 1번기어부터 4번기어까지 1, 2, 4, 8로 증가
            point *= 2;
        }
        // 정답 출력
        System.out.println(answer);
    }

    private static void command(int gearNum, int rotate) {
        // gear범위를 벗어나지 않는 선에서 진행
        // 톱니바퀴의 3시, 9시를 비교해서 다를 경우 회전 호출
        if(gearNum-1 >= 1 && gear[gearNum-1][three[gearNum-1]] != gear[gearNum][nine[gearNum]]) left(gearNum-1, -rotate);
        if(gearNum+1 <= 4 && gear[gearNum][three[gearNum]] != gear[gearNum+1][nine[gearNum+1]]) right(gearNum+1, -rotate);

        // 입력된 명령에 맞게 톱니 idx를 바꿔줌
        changeGear(gearNum, rotate);
    }
    private static void left(int gearNum, int rotate) {
        if(gearNum-1 >= 1 && gear[gearNum-1][three[gearNum-1]] != gear[gearNum][nine[gearNum]]) left(gearNum-1, -rotate);

        // 입력된 명령에 맞게 톱니 idx를 바꿔줌
        changeGear(gearNum, rotate);
    }
    private static void right(int gearNum, int rotate) {
        if(gearNum+1 <= 4 && gear[gearNum][three[gearNum]] != gear[gearNum+1][nine[gearNum+1]]) right(gearNum+1, -rotate);

        // 입력된 명령에 맞게 톱니 idx를 바꿔줌
        changeGear(gearNum, rotate);
    }
    // 방향 바꿔주는 method (idx가 범위 밖을 벗어나지 않도록 설정)
    private static void changeGear(int gearNum, int rotate) {
        twelve[gearNum] = (twelve[gearNum] - rotate + 8) % 8;
        three[gearNum] = (three[gearNum] - rotate + 8) % 8;
        nine[gearNum] = (nine[gearNum] - rotate + 8) % 8;
    }
}