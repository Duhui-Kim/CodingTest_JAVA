import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {    
    
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        
        if(X == Y) {
        	System.out.println(0);
        	return;
        }
        
        int gap = Y - X;
        
        int cnt = 0;
        long num = 0;
        long addNum = 1;
        
        while(num < gap) {
        	cnt++;        	
        	num += addNum;
        	
        	if(num >= gap) break;
        	
        	cnt++;
        	num += addNum;
        	if(num >= gap) break;
        	
        	addNum++;
        }
        System.out.println(cnt);
    }
}