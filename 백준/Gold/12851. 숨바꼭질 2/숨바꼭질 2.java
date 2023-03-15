import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        int size = 100002;

        if(K <= N) {
            System.out.printf("%d\n%d", N - K, 1);
            return;
        }

        int[] time = new int[size];

        Queue<Integer> queue = new LinkedList<>();


        queue.offer(N);

        int min = 0;
        int cnt = 0;
        int tmp = 0;

        boolean end = false;
        loop:
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                int x = queue.poll();

                for (int i = 0; i < 3; i++) {
                    int dx = x;
                    switch (i) {
                        case 0:
                            dx += 1;
                            break;
                        case 1:
                            dx -= 1;
                            break;
                        case 2:
                            dx *= 2;
                            break;
                    }
                    if (dx < 0 || dx >= size || dx == N) continue;

                    if (dx == K) {
                        if (time[K] == 0) {
                            time[K] = time[x] + 1;
                            min = time[K];
                            cnt++;
                            end = true;
                        } else if (time[K] == time[x] + 1) {
                            cnt++;
                        } else {
                            break loop;
                        }
                    } else if (!end && time[dx] == 0) {
                        time[dx] = time[x] + 1;
                        queue.offer(dx);
                    } else if (time[dx] == time[x] + 1) {
                        queue.offer(dx);
                    }
                }
            }
        }
        System.out.printf("%d\n%d", min, cnt);
    }
}