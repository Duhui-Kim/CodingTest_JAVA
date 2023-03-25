import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        // N이 0이거나 1이면 경우의 수 없음
        if(N < 2) {
            System.out.println(0);
            return;
        }

        // 소수 저장할 배열
        boolean[] prime = new boolean[N+2];
        prime[0] = true;
        prime[1] = true;

        // 에라토스테네스의 체 원리
        for (int i = 2; i * i <= N+1; i++) {
            if(prime[i]) continue;
            for (int j = i * i; j <= N+1; j += i) {
                prime[j] = true;
            }
        }
        
        // 포인터 두 개
        int p = 0;
        int q = 0;
        
        // 소수의 합 sum과 경우의 수 cnt
        int sum = 0;
        int cnt = 0;

        // q가 N을 넘어가면 불가능하므로 N 이하에서만 진행
        while (q <= N) {
            // 합이 N보다 작으면 q를 다음 소수까지 올려서 해당 소수값 더해줌
            if(sum < N) {
                q++;
                while (q <= N && prime[q]) q++;
                sum += q;
            }
            // 합이 N보다 크면 p를 다움 소수까지 올려서 해당 소수값 빼줌
            else if (sum > N) {
                p++;
                while (prime[p]) p++;
                sum -= p;
            } 
            // 값이 같으면 cnt 증가시키고 q를 다음 소수로 이동시킴
            else {
                cnt++;
                
                q++;
                while (q <= N && prime[q]) q++;
                sum += q;
            }
        }
        // 경우의 수 출력
        System.out.println(cnt);
    }
}