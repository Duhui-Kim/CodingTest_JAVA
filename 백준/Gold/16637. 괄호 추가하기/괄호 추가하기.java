import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static final char PLUS = '+';
    private static final char MINUS = '-';
    private static final char MULTI = '*';
    private static int N;
    private static long result = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        if (N == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }

        int[] number = new int[N/2 + 1];
        char[] operator = new char[N/2];

        int numIdx = 0;
        int operIdx = 0;

        for (char c : br.readLine().toCharArray()) {
            if (c - '0' < 0) {
                operator[operIdx++] = c;
            } else {
                number[numIdx++] = c - '0';
            }
        }
        boolean[] check = new boolean[N/2];

        backTracking(number, operator, check, 0, N/2);

        System.out.println(result);
    }

    private static void backTracking(int[] number, char[] operator, boolean[] check, int depth, int end) {
        if (depth >= end) {
            int idx = 0;
            long[] answer = new long[N/2 + 1];

            for (int i = 0; i < end; i++) {
                if (check[i]) {
                    answer[idx++] = calculate(number[i], number[i+1], operator[i]);
                    i++;
                } else {
                    answer[idx++] = number[i];
                }
                if (i+1 == end && !check[i]) answer[idx++] = number[i+1];
            }
            idx = 1;
            long num = answer[0];
            for (int i = 0; i < end; i++) {
                if (!check[i]) {
                    num = calculate(num, answer[idx++], operator[i]);
                }
            }
            result = Math.max(result, num);
            return;
        }

        if (depth > 0) {
            if (!check[depth-1]) {
                check[depth] = true;
                backTracking(number, operator, check, depth+1, end);
                check[depth] = false;
            }
            backTracking(number, operator, check, depth+1, end);
        } else {
            backTracking(number, operator, check, depth+1, end);

            check[depth] = true;
            backTracking(number, operator, check, depth+1, end);
            check[depth] = false;
        }

    }

    private static long calculate(long num1, long num2, char c) {
        switch (c) {
            case PLUS:
                return num1 + num2;
            case MINUS:
                return num1 - num2;
            case MULTI:
                return num1 * num2;
        }
        return 0;
    }


}