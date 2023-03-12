import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        boolean[] prime = new boolean[250001];
        prime[0] = true;
        prime[1] = true;

        for (int i = 2; i * i <= 250000; i++) {
            if (prime[i]) continue;

            for (int j = i * i; j <= 250000; j += i) {
                prime[j] = true;
            }
        }

        while (true) {
            int N = sc.nextInt();
            if(N == 0) break;

            int cnt = 0;
            for (int i = N+1; i <= 2 * N; i++) {
                if(!prime[i]) cnt++;
            }

            sb.append(cnt + "\n");
        }
        System.out.println(sb);
    }
}