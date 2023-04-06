import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        char[] a = sc.next().toCharArray();
        char[] b = sc.next().toCharArray();

        int[] arr = new int[30];

        for (int i = 0; i < a.length; i++) {
            arr[a[i] - 'a']++;
        }
        for (int i = 0; i < b.length; i++) {
            arr[b[i] - 'a']--;
        }

        int answer = 0;
        for (int i = 0; i < arr.length; i++) {
            answer += Math.abs(arr[i]);
        }
        System.out.println(answer);
    }
}