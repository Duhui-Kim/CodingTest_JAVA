import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class Main {    
	
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        long[] pay = new long[N];
        for(int i=0; i<N; i++) {
        	pay[i] = Long.parseLong(st.nextToken());
        }
        
        long sum = 0;
        
        for(int i=0; i<M; i++) {
        	sum += pay[i];
        }
        
        long answer = sum;
        for(int i=M; i<N; i++) {
        	sum -= pay[i-M];
        	sum += pay[i];
        	
        	answer = answer < sum ? sum : answer;
        }
        
        System.out.println(answer);
    }
}