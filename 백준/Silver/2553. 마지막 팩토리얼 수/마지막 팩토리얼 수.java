import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	private static Long N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Long.parseLong(br.readLine());
        long k = 1;
        
        for(int i=1; i<=N; i++) {
        	k = k * i;
        	while(true) {
        		// 0이 마지막일 경우 마지막 자릿수 제거
        		if(k%10 == 0) {
        			k/=10;
        		} else {
        			k%=400000000;
        			break;
        		}
        	} 	
        }
    	while(true) {
    		// 0이 마지막일 경우 마지막 자릿수 제거
    		if(k%10 == 0) {
    			k/=10;
    		} else {
    			k%=10;
    			break;
    		}
    	} 	
    	System.out.println(k);
	}
}
