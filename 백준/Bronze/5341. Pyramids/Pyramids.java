import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        StringBuilder sb = new StringBuilder();
        while(true) {
            long n = sc.nextLong();
            if(n == 0) break;
            sb.append((n * (n+1) / 2)).append("\n");
        }
        System.out.println(sb);
    }
}