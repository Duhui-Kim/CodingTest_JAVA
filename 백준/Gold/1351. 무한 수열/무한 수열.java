import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());
        
        long N = Long.parseLong(st.nextToken());
        long P = Long.parseLong(st.nextToken());
        long Q = Long.parseLong(st.nextToken());
        
        Map<Long, Long> map = new HashMap<>();
        
        System.out.println(findSum(N, P, Q, map));
    }

	private static Long findSum(long N, long P, long Q, Map<Long, Long> map) {
		if(N == 0) return 1l;
		if(map.get(N) != null) return map.get(N);
		
		Long sum = findSum(N/P, P, Q, map) + findSum(N/Q, P, Q, map);
		map.put(N, sum);
		
		return sum;
	}
}