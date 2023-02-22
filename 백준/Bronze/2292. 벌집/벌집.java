import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 찾을 숫자 N을 입력받는다
        int N = sc.nextInt();

        // 시작점을 1로 설정한다
        int start = 1;

        // i=1부터 시작해서 N이 start보다 작아지는 시점의 i를 출력한다.
        int i=1;
        while(N > start){
            start += 6 * i;
            i++;
        }
        System.out.println(i);
    }
}
