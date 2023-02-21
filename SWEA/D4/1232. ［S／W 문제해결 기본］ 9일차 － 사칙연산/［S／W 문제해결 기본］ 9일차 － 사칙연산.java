import java.util.Scanner;

public class Solution {
    private static String[] input;
    public static void main(String[] args) {
        // 숫자를 입력받고 String 배열을 만든다.
        Scanner sc = new Scanner(System.in);

        for(int t=1; t<=10; t++) {
            int N = Integer.parseInt(sc.nextLine());

            input = new String[N + 1];

            // String을 입력받으면 차례로 1 ~ size 배열에 넣는다.
            for (int i = 1; i <= N; i++) {
                input[i] = sc.nextLine();
            }

            // 트리 형태이므로 맨 위의 값인 첫 번째 String부터 시작하면 된다.
            // 꺼낸 String의 1번째 값이 연산자이면 해당 연산 method 호출 / 숫자이면 계산
            int answer = 0;

            if (input[1].split(" ")[1].equals("+")) {
                answer = addFunc(Integer.parseInt(input[1].split(" ")[2]), Integer.parseInt(input[1].split(" ")[3]));
            } else if (input[1].split(" ")[1].equals("-")) {
                answer = subFunc(Integer.parseInt(input[1].split(" ")[2]), Integer.parseInt(input[1].split(" ")[3]));
            } else if (input[1].split(" ")[1].equals("*")) {
                answer = multiFunc(Integer.parseInt(input[1].split(" ")[2]), Integer.parseInt(input[1].split(" ")[3]));
            } else if (input[1].split(" ")[1].equals("/")) {
                answer = divFunc(Integer.parseInt(input[1].split(" ")[2]), Integer.parseInt(input[1].split(" ")[3]));
            }
            System.out.printf("#%d %d\n", t, answer);
        }
    }

    // 아래의 다른 function은 리턴값만 다르기 때문에 하나만 설명하자면,
    private static int addFunc(int a, int b) {
        // 계산을 수행할 값을 넣을 A와 B를 선언한다.
        int A = 0;
        int B = 0;

        // 정수 a, b를 받아서 input에 넣으면 해당 idx의 String이 호출된다.
        // 이 때 String을 split한 값의 두 번째는 연산자이거나, 숫자이다.
        // 연산자일 경우 세 번째 값과 네 번째 값은 정수이므로 새로운 함수를 호출한다.
        // 숫자일 경우 A 또는 B에 저장하여 계산한다.
        if(input[a].split(" ")[1].equals("+")){
            A = addFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("-")){
            A = subFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("*")){
            A = multiFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("/")) {
            A = divFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else {
            A = Integer.parseInt(input[a].split(" ")[1]);
        }

        if(input[b].split(" ")[1].equals("+")){
            B = addFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("-")){
            B = subFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("*")){
            B = multiFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("/")) {
            B = divFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else {
            B = Integer.parseInt(input[b].split(" ")[1]);
        }

        return A + B;
    }

    private static int subFunc(int a, int b) {
        int A = 0;
        int B = 0;

        if(input[a].split(" ")[1].equals("+")){
            A = addFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("-")){
            A = subFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("*")){
            A = multiFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("/")) {
            A = divFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else {
            A = Integer.parseInt(input[a].split(" ")[1]);
        }

        if(input[b].split(" ")[1].equals("+")){
            B = addFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("-")){
            B = subFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("*")){
            B = multiFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("/")) {
            B = divFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else {
            B = Integer.parseInt(input[b].split(" ")[1]);
        }

        return Math.abs(A - B);
    }

    private static int multiFunc(int a, int b) {
        int A = 0;
        int B = 0;

        if(input[a].split(" ")[1].equals("+")){
            A = addFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("-")){
            A = subFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("*")){
            A = multiFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("/")) {
            A = divFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else {
            A = Integer.parseInt(input[a].split(" ")[1]);
        }

        if(input[b].split(" ")[1].equals("+")){
            B = addFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("-")){
            B = subFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("*")){
            B = multiFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("/")) {
            B = divFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else {
            B = Integer.parseInt(input[b].split(" ")[1]);
        }

        return A * B;
    }

    private static int divFunc(int a, int b) {
        int A = 0;
        int B = 0;

        if(input[a].split(" ")[1].equals("+")){
            A = addFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("-")){
            A = subFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("*")){
            A = multiFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else if(input[a].split(" ")[1].equals("/")) {
            A = divFunc(Integer.parseInt(input[a].split(" ")[2]), Integer.parseInt(input[a].split(" ")[3]));
        } else {
            A = Integer.parseInt(input[a].split(" ")[1]);
        }

        if(input[b].split(" ")[1].equals("+")){
            B = addFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("-")){
            B = subFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("*")){
            B = multiFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else if(input[b].split(" ")[1].equals("/")) {
            B = divFunc(Integer.parseInt(input[b].split(" ")[2]), Integer.parseInt(input[b].split(" ")[3]));
        } else {
            B = Integer.parseInt(input[b].split(" ")[1]);
        }
        if(A >= B) return A / B;
        else return B / A;
    }
}
