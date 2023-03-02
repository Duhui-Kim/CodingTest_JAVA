import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    // 0부터 3까지 UP, DOWN, LEFT, RIGHT
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, -1, 1};
    private static int H;
    private static int W;
    private static int x;
    private static int y;
    private static int dir;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= testCase; i++) {
            StringTokenizer st = new StringTokenizer(sc.nextLine());

            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            // map을 저장한다.
            char[][] map = new char[H][W];

            for (int j = 0; j < H; j++) {
                map[j] = sc.nextLine().toCharArray();
            }
            
            x = 0;
            y = 0;
            dir = 0;

            // 전차를 찾으면 그 위치와 방향을 저장
            loop:
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    if(map[j][k] == '^' || map[j][k] == 'v' || map[j][k] == '<' || map[j][k] == '>') {
                        findTank(j, k, map[j][k]);
                        break loop;
                    }
                }
            }

            // 명령어의 개수를 입력받아 해당 크기의 char 배열을 만들고,
            // 입력된 내용을 command에 넣음
            int cNum = Integer.parseInt(sc.nextLine());
            char[] command = new char[cNum];

            command = sc.nextLine().toCharArray();

            // command가 S이면 shoot
            // S가 아니면 이동 명령
            for (int j = 0; j < cNum; j++) {
                if(command[j] == 'S') {
                    shoot(map);
                } else {
                    move(map, command[j]);
                }
            }

            // 결과 출력
            sb.append("#" + i + " ");
            for (int j = 0; j < H; j++) {
                for (int k = 0; k < W; k++) {
                    sb.append(map[j][k]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    private static void move(char[][] map, char c) {
        // 명령어가 주어지면 방향을 설정하고 현재 위치를 '.'으로 바꿈
        if(c == 'U') dir = 0;
        else if (c == 'D') dir = 1;
        else if (c == 'L') dir = 2;
        else dir = 3;

        map[x][y] = '.';

        // 해당 방향으로 이동할 수 있으면 이동
        if(x+dx[dir] >= 0 && y+dy[dir] >= 0 && x+dx[dir] < H && y+dy[dir] < W && map[x+dx[dir]][y+dy[dir]] == '.') {
            x += dx[dir];
            y += dy[dir];
        }

        // 전차 위치 표시
        if(dir == 0) map[x][y] = '^';
        else if (dir == 1) map[x][y] = 'v';
        else if (dir == 2) map[x][y] = '<';
        else map[x][y] = '>';
    }

    private static void shoot(char[][] map) {
        // 방향에 따라 포탄 날아가는 방향이 달라짐
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 범위 밖을 벗어나면 종료
        while (nx >= 0 && ny >= 0 && nx < H && ny < W) {
            // 벽을 만났을 때 벽돌이면 평지로 바꾸고 강철이면 그냥 끝남
            if(map[nx][ny] == '#') {
                break;
            } else if (map[nx][ny] == '*') {
                map[nx][ny] = '.';
                break;
            }
            nx += dx[dir];
            ny += dy[dir];
        }
    }

    // 처음에 탱크 위치 찾고 방향 정하는 method
    private static void findTank(int i, int j, char input) {
        x = i;
        y = j;

        if(input == '^') dir = 0;
        else if (input == 'v') dir = 1;
        else if (input == '<') dir = 2;
        else dir = 3;
    }
}