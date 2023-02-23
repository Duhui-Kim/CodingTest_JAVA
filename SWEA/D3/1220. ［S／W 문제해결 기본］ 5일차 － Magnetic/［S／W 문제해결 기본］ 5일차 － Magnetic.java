import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 10개의 테스트케이스를 진행한다.
        for (int t = 1; t <= 10; t++) {
            
            // size와 map을 입력받는다.
            int size = sc.nextInt();
            int[][] map = new int[size][size];

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    map[i][j] = sc.nextInt();
                }
            }
            
            /*
            cnt를 0으로 설정하고 각 열마다 위에서부터 아래로 내려오며 진행한다.
            boolean check를 써서 1과 2의 쌍을 체크해줬다.
            check가 false일 때 1을 만나면 check를 true로 바꾸고
            check가 true이고 2를 만나면 check를 다시 false로 바꾼 뒤 cnt를 증가시킨다. 
             */
            int cnt = 0;
            for (int j = 0; j < size; j++) {
                boolean check = false;
                for (int i = 0; i < size; i++) {
                    if(!check && map[i][j] == 1){
                        check = true;
                    } else if (check && map[i][j] == 2){
                        check = false;
                        cnt++;
                    }
                }
            }
            // 값을 출력한다.
            System.out.printf("#%d %d\n", t, cnt);
        }
    }
}
