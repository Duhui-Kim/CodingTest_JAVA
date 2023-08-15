import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {    
    public static class Alpha {
    	long value;
    	boolean zero = true;
    	
    	public Alpha() {}
    	
    	public void addNum(long num) {
    		value += num;
    	}
    }
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Alpha[] alphabets = new Alpha[10];
        
        for(int i=0; i<10; i++) {
        	alphabets[i] = new Alpha();
        }
        
        for(int i=0; i<N; i++) {
        	char[] nxt = br.readLine().toCharArray();
        	int length = nxt.length;
        			
        	for(int j=0; j<length; j++) {
        		long num = 1;
        		int zero = length - 1 - j;
        		
        		while(zero-- > 0) {
        			num *= 10;
        		}
        		if(j == 0) {
        			alphabets[nxt[j] - 'A'].zero = false;
        		}
        		alphabets[nxt[j] - 'A'].addNum(num);
        	}
        }        
        Arrays.sort(alphabets, (o1, o2) -> Long.compare(o1.value, o2.value));
        
        for(int i=0; i<10; i++) {
        	if(!alphabets[i].zero) continue;
        	
        	alphabets[i].value = 0L;
        	break;
        }
        
        Arrays.sort(alphabets, (o1, o2) -> Long.compare(o2.value, o1.value));
        long answer = 0;
        long cnt = 9;
        for(int i=0; i<10; i++) {
        	answer += alphabets[i].value * cnt;
        	cnt--;
        }
        System.out.println(answer);
    }
}