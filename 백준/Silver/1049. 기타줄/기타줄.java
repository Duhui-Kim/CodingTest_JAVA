import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int minPackagePrice = Integer.MAX_VALUE;
        int minSinglePrice = Integer.MAX_VALUE;
        
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            
            int packagePrice = Integer.parseInt(st.nextToken());
            int singlePrice = Integer.parseInt(st.nextToken());
            
            minPackagePrice = minPackagePrice > packagePrice ? packagePrice : minPackagePrice;
            minSinglePrice = minSinglePrice > singlePrice ? singlePrice : minSinglePrice;
        }
        
        int[] minPrice = new int[N+1];
        
        for (int i=1; i<=N; i++) {
            minPrice[i] = minPrice[i-1] + minSinglePrice;
        }
        
        for (int i=1; i<=N; i++) {
            int tempPrice = i-6 >= 0 ? minPrice[i-6] + minPackagePrice : minPackagePrice;
            minPrice[i] = tempPrice < minPrice[i] ? tempPrice : minPrice[i];
        }
        
        System.out.println(minPrice[N]);
    }
}