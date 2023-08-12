import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {    
    
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int target = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        
        boolean[] check = new boolean[10];

        if(n != 0) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < n; i++) {
        		int num = Integer.parseInt(st.nextToken());
        		check[num] = true;
        	}
        }
        
        int result = Math.abs(target - 100);
        for(int i = 0; i <= 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();
            
            boolean isBreak = false;
            for(int j = 0; j < len; j++) {
                if(check[str.charAt(j) - '0']) {
                    isBreak = true; 
                    break;
                }
            }
            if(!isBreak) {
                int min = Math.abs(target - i) + len;
                result = Math.min(min, result);
            }
        }        
        System.out.println(result);
    }
}