import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        // 길이 순 -> 숫자의 합 -> 사전 순
        PriorityQueue<Word> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o1.length != o2.length) return o1.length - o2.length;
            if (o1.sum != o2.sum) return o1.sum - o2.sum;
            return o1.word.compareTo(o2.word);
        }));

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            int length = input.length();
            int sum = 0;
            for (int j = 0; j < length; j++) {
                if(input.charAt(j) <= '9' && input.charAt(j) >= '1') {
                    sum += input.charAt(j) - '0';
                }
            }
            queue.offer(new Word(input, length, sum));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(queue.poll().word).append("\n");
        }
        System.out.println(sb.toString());
    }
    static class Word {
        String word;
        int length;
        int sum;
        private Word(String word, int length, int sum) {
            this.word = word;
            this.length = length;
            this.sum = sum;
        }
    }
}