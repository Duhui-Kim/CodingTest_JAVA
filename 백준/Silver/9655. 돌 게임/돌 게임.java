import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 규칙을 보면 6의 경우에는 5에서 1을 더하거나 3에서 3을 더한 값이다.
        // 따라서 두 경우의 수에서 이기는 사람이 같으면 6에서 이기는 사람은 항상 바뀐다.
        int N = sc.nextInt();

        if(N % 2 == 0) System.out.println("CY");
        else System.out.println("SK");
    }
}
