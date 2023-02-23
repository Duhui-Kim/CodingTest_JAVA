import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        /*
         스택에 차례로 넣으면서 진행
         스택이 비어있으면 push
         스택에 수가 있는데 자기보다 작으면 cnt++하고 pop 계속

         같으면을 어떻게 처리할까??
         same++; -> same만큼 stack에 넣어줌

         자기보다 크면 cnt++하고 queue에 있는거 다 넣고 본인도 push
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        ArrayDeque<long[]> stack = new ArrayDeque<>();

        long cnt = 0;
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            long[] a = new long[2];
            a[0] = num;
            a[1] = 1;
            while (!stack.isEmpty()) {
                if(num > stack.peekLast()[0]){
                    cnt += stack.pollLast()[1];
                } else if (num == stack.peekLast()[0]) {
                    a[1] += stack.peekLast()[1];
                    cnt += stack.pollLast()[1];
                } else {
                    cnt++;
                    break;
                }
            }
            stack.addLast(a);

        }
        System.out.println(cnt);

        /*
        투포인터로도 가능하지않을까?? -> 실 패 !
        배열에 숫자들 순서대로 넣고, start는 0부터 N-2까지, end은 1부터 n-1까지
        시작하자마자 cnt++하고 start+1을 max로 설정해놓음
        max > start값 or max > (end+1) or end가 n-1까지 가면 종료
        그렇지 않으면 end를 한 칸 증가하고 end와 max 중 큰 값을 max로 대체
         */

    }
}
