import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int K = sc.nextInt();
        
        // 학년이 1~6으로 주어지므로 7칸짜리 배열을 만든다.
        int[][] student = new int[2][7];
        
        // student 배열에 값을 입력받고
        for (int i = 0; i < N; i++) {
            student[sc.nextInt()][sc.nextInt()]++;
        }

        int room = 0;
        
        // 배열을 순회하며 0이 아닌 값일 때 조건에 맞는 방 수를 측정한다.
        for (int i = 0; i < 2; i++) {
            for (int j = 1; j < 7; j++) {
                if(student[i][j] != 0) {
                    if(student[i][j] % K == 0){
                        room += student[i][j] / K;
                    } else {
                        room += student[i][j] / K + 1;
                    }
                }
            }
        }
        
        // 방의 개수를 출력
        System.out.println(room);
    }
}
