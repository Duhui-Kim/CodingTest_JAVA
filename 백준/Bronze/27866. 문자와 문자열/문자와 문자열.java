import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        char[] input = sc.nextLine().toCharArray();
        int N = Integer.parseInt(sc.nextLine());
        
        System.out.println(input[N-1]);
    }
}