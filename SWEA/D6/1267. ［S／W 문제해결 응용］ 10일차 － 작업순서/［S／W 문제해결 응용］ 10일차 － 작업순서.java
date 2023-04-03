import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		for(int tc = 1; tc <= 10; tc++) {			
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			
			sb.append("#").append(tc).append(" ");
			
			// 정점의 개수와 간선의 개수 받음
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 인접리스트 생성
			ArrayList<Integer>[] list = new ArrayList[V+1];
			
			// indegree 개수 저장할 배열
			int[] indegree = new int[V+1];
			
			// a -> b의 수를 받아서 인접리스트와 indegree에 저장
			st = new StringTokenizer(sc.nextLine());
			for(int i=0; i<E; i++) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
								
				if(list[a] == null) list[a] = new ArrayList<>();
				
				list[a].add(b);
				indegree[b]++;
			}
			
			// 선행조건 없는 애들을 queue에 넣기
			Queue<Integer> queue = new LinkedList<>();
			for(int i=V; i>=1; i--) {
				if(indegree[i] == 0) {
					queue.offer(i);
				}
			}

			// queue가 빌 때까지 진행
			while(!queue.isEmpty()) {
				
				// queue에서 숫자를 꺼내고 기록한다.
				int num = queue.poll();
				sb.append(num).append(" ");
				
				// list가 비어있으면 다음 숫자로
				if(list[num] == null) continue;
				
				// list에 있는 숫자에 대해서 indegree를 1씩 줄여준다.
				for(int a : list[num]) {
					indegree[a]--;
					
					// indegree가 0이 되었다면 queue에 넣는다.
					if(indegree[a] == 0) queue.offer(a);
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}