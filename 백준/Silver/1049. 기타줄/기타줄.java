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
        
        int packageCount = N / 6;
        int singleCount = N % 6;
        
        int minPrice = minPackagePrice > minSinglePrice * 6 ? 
            packageCount * minSinglePrice * 6 : 
            packageCount * minPackagePrice;
        
        minPrice += minPackagePrice > minSinglePrice * singleCount ?
            singleCount * minSinglePrice :
            minPackagePrice;
        
        System.out.println(minPrice);
    }
}