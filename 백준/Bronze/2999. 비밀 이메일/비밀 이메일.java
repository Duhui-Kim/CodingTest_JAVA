import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        String str = sc.nextLine();

        int N = str.length();

        // R, C의 초기값을 루트 N으로 설정 (정수값)
        int R = (int)Math.sqrt(N);
        int C = (int)Math.sqrt(N);

        // 곱한 값이 N보다 작으면 C를 증가시키고,
        // N보다 크면 R을 감소시키고, 같을 경우 종료한다.
        while(true) {
            if(N > R * C) {
                C++;
            } else if(N < R * C){
                R--;
            } else {
                break;
            }
        }

        // R이 구해졌으므로 순서에 맞게 출력하면 된다.
        int idx = 0;

        while (idx/N != R) {
            sb.append(str.charAt(idx%N + idx/N) + "");
            idx+=R;
        }
        System.out.println(sb);
    }
}
