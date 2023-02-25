import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int L = sc.nextInt();
        int N = sc.nextInt();

        // L 크기의 롤케이크를 만든다.
        int[] cake = new int[L+1];

        // 원하는 개수의 최댓값과 그 때의 사람 번호
        // 받는 개수의 최댓값과 그 때의 사람 번호
        int max_want = 0;
        int p1 = 0;
        int max_receive = 0;
        int p2 = 0;

        // N명의 사람 각각의 번호를 입력받고, 두 수의 차이가 원하는 개수의 최댓값보다 크면
        // 최댓값을 갱신하고 사람 번호를 저장한다.
        // 그리고 s부터 e까지 가져갈 수 있는 케이크를 cnt하고 최댓값과 비교하여
        // 사람 번호를 저장한다.
        for (int i = 1; i <= N; i++) {
            int s = sc.nextInt();
            int e = sc.nextInt();

            if(e-s > max_want) {
                max_want = e-s;
                p1 = i;
            }

            int cnt = 0;
            for (int j = s; j <= e; j++) {
                if(cake[j] == 0) {
                    cake[j] = i;
                    cnt++;
                }
            }
            if(cnt > max_receive) {
                max_receive = cnt;
                p2 = i;
            }
        }
        System.out.println(p1);
        System.out.println(p2);
    }
}
