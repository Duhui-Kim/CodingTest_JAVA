import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
 
public class Main {    
	public static Set<Integer> set;
	
    public static void main(String[] args) throws IOException  {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        int answer = 0;
        
        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        
        if(K == 0) {
        	System.out.println(M);
        	return;
        }
               
        int[] parent = new int[N+1];
        
        for(int i=1; i<=N; i++) {
        	parent[i] = i;
        }
        
        set = new HashSet<>();
    
        for(int i=0; i<K; i++) {
        	set.add(Integer.parseInt(st.nextToken()));
        }
        
        List<Integer>[] lists = new LinkedList[M];
        
        for(int i=0; i<M; i++) {
        	lists[i] = new LinkedList<>();
        	
        	st = new StringTokenizer(br.readLine());
        	int peopleCnt = Integer.parseInt(st.nextToken());
        	
        	int first = Integer.parseInt(st.nextToken());
        	lists[i].add(first);
        	for(int j=1; j<peopleCnt; j++) {        	
        		int nxt = Integer.parseInt(st.nextToken());
        		
        		union(parent, parent[nxt], parent[first]);
        		lists[i].add(nxt);
        	}
        }
                
        for(int i=0; i<M; i++) {
			boolean flag = true;
			for(int num : lists[i]) {
				if(set.contains(getParent(parent, parent[num]))) {
                    flag= false;
                    break;
    			}
            }
			if(flag) {
				answer++;
			}
		}      
        System.out.println(answer);
    }

	private static void union(int[] parent, int a, int b) {
		a = getParent(parent, a);
		b = getParent(parent, b);
		
		if(set.contains(b)) {
			parent[a] = b;
		} else {
			parent[b] = a;			
		}
	}

	private static int getParent(int[] parent, int idx) {
		if(parent[idx] == idx) return idx;
		
		return parent[idx] = getParent(parent, parent[idx]);
	}

}