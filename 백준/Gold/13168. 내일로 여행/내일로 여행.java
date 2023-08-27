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
        
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        
        // 도시 입력받으면서 index 부여
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
        	String nxt = st.nextToken();
        	
        	if(map.containsKey(nxt)) continue;
        	map.put(nxt, index++);
        }
        
        // 코스 입력받기
        int M = Integer.parseInt(br.readLine());
        int[] course = new int[M];
        
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++) {
        	course[i] = map.get(st.nextToken());
        }
        
        // 최단경로 저장용
        double[][][] shortest = new double[2][index][index];
        
        for(int k=0; k<2; k++) {
        	for(int i=0; i<index; i++) {
        		for(int j=0; j<index; j++) {
        			if(i == j) continue;
        			shortest[k][i][j] = Integer.MAX_VALUE;
        		}
        	}        	
        }
        
        // 내일로 티켓
        // Mugunghwa, ITX-Saemaeul, ITX-Cheongchun 무료
        // S-Train, V-Train 50% 할인
        Set<String> noCost = new HashSet<>();
        noCost.add("Mugunghwa");
        noCost.add("ITX-Saemaeul");
        noCost.add("ITX-Cheongchun");
               
        Set<String> halfCost = new HashSet<>();	
        halfCost.add("S-Train");
        halfCost.add("V-Train");
        
        int K = Integer.parseInt(br.readLine());
        
        for(int i=0; i<K; i++) {
        	st = new StringTokenizer(br.readLine());
        	
        	String type = st.nextToken();
        	int from = map.get(st.nextToken());
        	int to = map.get(st.nextToken());
        	double cost = Integer.parseInt(st.nextToken());
        	
        	// 티켓 안 샀을 때 최소 금액 저장
        	if(shortest[0][from][to] > cost) {
        		shortest[0][from][to] = cost;
        		shortest[0][to][from] = cost;
        	}
        	
        	// 티켓 샀을 때 최소 금액 저장
        	double ticketCost = cost;
        	if(noCost.contains(type)) {
        		ticketCost = 0;
        	} else if (halfCost.contains(type)) {
        		ticketCost /= 2;
        	}
        	
        	if(shortest[1][from][to] > ticketCost) {
        		shortest[1][from][to] = ticketCost;
        		shortest[1][to][from] = ticketCost;
        	}
        }
        
        for(int k=0; k<index; k++) {
        	for(int i=0; i<index; i++) {
        		for(int j=0; j<index; j++) {        			
        			if(shortest[0][i][j] > shortest[0][i][k] + shortest[0][k][j]) {
    					shortest[0][i][j] = shortest[0][i][k] + shortest[0][k][j];
    				}
        			
        			if(shortest[1][i][j] > shortest[1][i][k] + shortest[1][k][j]) {
    					shortest[1][i][j] = shortest[1][i][k] + shortest[1][k][j];
    				}
        		}
        	}
        }
       
        double normal = 0;
        double ticket = R; 
        
        for(int i=1; i<M; i++) {        	
        	normal += shortest[0][course[i-1]][course[i]];
        	ticket += shortest[1][course[i-1]][course[i]];
        }
        
        if(ticket < normal) {
        	System.out.println("Yes");
        } else {
        	System.out.println("No");
        }
    }
}