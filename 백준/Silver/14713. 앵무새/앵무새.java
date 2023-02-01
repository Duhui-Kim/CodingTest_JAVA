import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
						
		// 숫자 n을 입력받는다.
		int n = Integer.parseInt(br.readLine());
		
		// queue 담은 리스트를 만든다
		List<Queue<String>> list = new ArrayList<>();
		
		// 각각의 queue에 한 문장을 쪼갠 단어를 입력한다.
		for(int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			list.add(new LinkedList<String>());
			while(st.hasMoreTokens()) {
				list.get(i).offer(st.nextToken());
			}
		}
		
		// 주어진 문장을 입력하고, 한 단어씩 쪼개서 리스트에 저장한다.	
		String[] slist = br.readLine().split(" ");
		
		// queue의 peek 값과 비교해서 같은 값이 있으면, poll 하고 다음 값을 비교한다.
		for(int j=0; j<slist.length; j++) {
			int a = 0;
			for(int i=0; i<list.size(); i++) {
				if(!list.get(i).isEmpty() && slist[j].equals(list.get(i).peek())) {
					a = list.get(i).poll().charAt(0);
					break;
				}
			}
			// 같은 값이 없으면 Impossible을 호출한다.
			if(a == 0) {
				System.out.println("Impossible");
				return;
			}
		}
		
		// queue에 값이  남아있어도 Impossible을 호출한다.
		for(Queue q : list) {
			if(!q.isEmpty()) {
				System.out.println("Impossible");
				return;
			}
		}
		// 완료되면 Possible을 호출한다.
		System.out.println("Possible");		
	}
}