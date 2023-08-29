import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
 
public class Main {    
	
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] costs = new int[C];
        Arrays.fill(costs, 1_000_000);
        costs[0] = 0;
        int answer = 1_000_000;
        
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	int cost = Integer.parseInt(st.nextToken());
        	int people = Integer.parseInt(st.nextToken());
        	
        	for(int j=0; j<C; j++) {
        		if(j+people < C && costs[j+people] > costs[j] + cost) {
        			costs[j+people] = costs[j] + cost;
        		} else if(j+people >= C) {
        			answer = answer < costs[j] + cost ? answer : costs[j] + cost;
        		}
        	}
        }
        System.out.println(answer);
    }
}