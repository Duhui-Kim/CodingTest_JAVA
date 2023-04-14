import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
                
        int[][] apart = new int[15][15];

        for (int i = 0; i < 15; i++) {
            apart[0][i] = i;
        }
        
        for (int i = 1; i < 15; i++) {
            for (int j = 1; j < 15; j++) {
                apart[i][j] = apart[i-1][j] + apart[i][j-1];
            }
        }
        
        int testCase = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < testCase; tc++) {
            int k = sc.nextInt();
            int n = sc.nextInt();

            sb.append(apart[k][n]).append("\n");
        }
        System.out.println(sb);
    }
}