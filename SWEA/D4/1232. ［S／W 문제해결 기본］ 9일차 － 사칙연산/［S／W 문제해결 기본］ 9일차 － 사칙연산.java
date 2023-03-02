import java.util.Scanner;

public class Solution {

    private static String[] arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {

            int N = Integer.parseInt(sc.nextLine());

            // N+1 크기의 배열을 만든 후 인덱스에 맞게 값을 넣어준다.
            arr = new String[N + 1];

            // 문자열 형태로 배열에 넣는다
            for (int i = 1; i <= N; i++) {
                arr[i] = sc.nextLine();
            }

            // 1번부터 진행한다.
            String[] calculate = arr[1].split(" ");

            // answer을 0으로 두고, 결과를 반환받는다.
            int answer = 0;

            // 1번 idx의 값이 연산자이면 연산자 메서드 호출
            if (calculate[1].equals("-")) {
                answer = minus(calculate[2], calculate[3]);
            } else if (calculate[1].equals("+")) {
                answer = plus(calculate[2], calculate[3]);
            } else if (calculate[1].equals("*")) {
                answer = multi(calculate[2], calculate[3]);
            } else if (calculate[1].equals("/")) {
                answer = div(calculate[2], calculate[3]);
            } else {
                answer = Integer.parseInt(calculate[1]);
            }

            // 결과를 출력.
            sb.append(String.format("#%d %d\n", t, answer));
        }
        System.out.println(sb);
    }

    private static int minus(String a, String b) {
        // a와 b를 받아서 해당 값의 1번째 idx 값이 연산자이면 또 연산자를 호출하고,
        // 둘 다 정수이면 계산한 결과를 반환한다.
        String[] A = arr[Integer.parseInt(a)].split(" ");
        String[] B = arr[Integer.parseInt(b)].split(" ");

        int resultA = 0;
        int resultB = 0;
        if(A[1].equals("-")) {
            resultA = minus(A[2], A[3]);
        } else if (A[1].equals("+")) {
            resultA = plus(A[2], A[3]);
        } else if (A[1].equals("*")) {
            resultA = multi(A[2], A[3]);
        } else if (A[1].equals("/")) {
            resultA = div(A[2], A[3]);
        } else {
            resultA = Integer.parseInt(A[1]);
        }

        if(B[1].equals("-")) {
            resultB = minus(B[2], B[3]);
        } else if (B[1].equals("+")) {
            resultB = plus(B[2], B[3]);
        } else if (B[1].equals("*")) {
            resultB = multi(B[2], B[3]);
        } else if (B[1].equals("/")) {
            resultB = div(B[2], B[3]);
        } else {
            resultB = Integer.parseInt(B[1]);
        }

        return resultA - resultB;
    }

    private static int plus(String a, String b) {
        String[] A = arr[Integer.parseInt(a)].split(" ");
        String[] B = arr[Integer.parseInt(b)].split(" ");

        int resultA = 0;
        int resultB = 0;
        if(A[1].equals("-")) {
            resultA = minus(A[2], A[3]);
        } else if (A[1].equals("+")) {
            resultA = plus(A[2], A[3]);
        } else if (A[1].equals("*")) {
            resultA = multi(A[2], A[3]);
        } else if (A[1].equals("/")) {
            resultA = div(A[2], A[3]);
        } else {
            resultA = Integer.parseInt(A[1]);
        }

        if(B[1].equals("-")) {
            resultB = minus(B[2], B[3]);
        } else if (B[1].equals("+")) {
            resultB = plus(B[2], B[3]);
        } else if (B[1].equals("*")) {
            resultB = multi(B[2], B[3]);
        } else if (B[1].equals("/")) {
            resultB = div(B[2], B[3]);
        } else {
            resultB = Integer.parseInt(B[1]);
        }

        return resultA + resultB;
    }

    private static int div(String a, String b) {
        String[] A = arr[Integer.parseInt(a)].split(" ");
        String[] B = arr[Integer.parseInt(b)].split(" ");

        int resultA = 0;
        int resultB = 0;
        if(A[1].equals("-")) {
            resultA = minus(A[2], A[3]);
        } else if (A[1].equals("+")) {
            resultA = plus(A[2], A[3]);
        } else if (A[1].equals("*")) {
            resultA = multi(A[2], A[3]);
        } else if (A[1].equals("/")) {
            resultA = div(A[2], A[3]);
        } else {
            resultA = Integer.parseInt(A[1]);
        }

        if(B[1].equals("-")) {
            resultB = minus(B[2], B[3]);
        } else if (B[1].equals("+")) {
            resultB = plus(B[2], B[3]);
        } else if (B[1].equals("*")) {
            resultB = multi(B[2], B[3]);
        } else if (B[1].equals("/")) {
            resultB = div(B[2], B[3]);
        } else {
            resultB = Integer.parseInt(B[1]);
        }

        return resultA / resultB;
    }

    private static int multi(String a, String b) {
        String[] A = arr[Integer.parseInt(a)].split(" ");
        String[] B = arr[Integer.parseInt(b)].split(" ");

        int resultA = 0;
        int resultB = 0;
        if(A[1].equals("-")) {
            resultA = minus(A[2], A[3]);
        } else if (A[1].equals("+")) {
            resultA = plus(A[2], A[3]);
        } else if (A[1].equals("*")) {
            resultA = multi(A[2], A[3]);
        } else if (A[1].equals("/")) {
            resultA = div(A[2], A[3]);
        } else {
            resultA = Integer.parseInt(A[1]);
        }

        if(B[1].equals("-")) {
            resultB = minus(B[2], B[3]);
        } else if (B[1].equals("+")) {
            resultB = plus(B[2], B[3]);
        } else if (B[1].equals("*")) {
            resultB = multi(B[2], B[3]);
        } else if (B[1].equals("/")) {
            resultB = div(B[2], B[3]);
        } else {
            resultB = Integer.parseInt(B[1]);
        }

        return resultA * resultB;
    }
}