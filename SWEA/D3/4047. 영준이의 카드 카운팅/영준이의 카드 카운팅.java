import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();

        for (int t = 1; t <= testCase; t++) {

            // 카드를 저장할 배열 선언
            int[][] arr = new int[4][14];

            // String을 입력받고 진행한다.
            String input = sc.next();

            // 겹치는 카드가 나오면 isImpossible을 true로 바꾼다.
            boolean isImpossible = false;
            loop:
            for (int i = 0; i < input.length()-2; i += 3) {
                // 항상 S, D, H, C와 숫자 두 자리가 나오므로 i는 3칸씩 움직인다.
                // num에 두 자리 숫자를 integer로 변환한 값을 넣고
                int num = Integer.parseInt(input.substring(i + 1, i + 3));

                switch (input.charAt(i)) {
                    // S, D, H, C에 따라 배열에 값을 저장한다.
                    // 이 때 이미 배열에 숫자가 들어가있다면 isImpossible을 true로 바꾸고 종료한다.
                    case 'S':
                        if(arr[0][num] == 0){
                            arr[0][num]++;
                            arr[0][0]++;
                        } else {
                            isImpossible = true;
                            break loop;
                        }
                        break;
                    case 'D':
                        if(arr[1][num] == 0){
                            arr[1][num]++;
                            arr[1][0]++;
                        } else {
                            isImpossible = true;
                            break loop;
                        }
                        break;
                    case 'H':
                        if(arr[2][num] == 0){
                            arr[2][num]++;
                            arr[2][0]++;
                        } else {
                            isImpossible = true;
                            break loop;
                        }
                        break;
                    case 'C':
                        if(arr[3][num] == 0){
                            arr[3][num]++;
                            arr[3][0]++;
                        } else {
                            isImpossible = true;
                            break loop;
                        }
                        break;
                }
            }
            if(isImpossible) {
                System.out.printf("#%d %s", t, "ERROR");
            } else {
                System.out.printf("#%d ", t);
                for (int i = 0; i < 4; i++) {
                    System.out.print((13 - arr[i][0]) + " ");
                }
            }
            System.out.println();
        }
    }
}
