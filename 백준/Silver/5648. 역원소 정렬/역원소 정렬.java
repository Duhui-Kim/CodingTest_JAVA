import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		// 첫 번째 숫자 n을 입력받고 해당 크기의 String 배열을 만든다.
		int n = Integer.parseInt(st.nextToken());
		String[] array = new String[n];
		
		// 입력 줄마다 원소 수는 랜덤하므로 다음과 같이 뽑아내어 array에 저장한다.
		int cnt = 0;
		while(cnt < n) {
			if(!st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			while(st.hasMoreTokens()) {
				array[cnt++] = st.nextToken();
			}
		}
		
		br.close();
		
		// 원소 수만큼 반복 -> 길이만큼 반복하여 거꾸로 str을 만든 뒤 List에 넣는다.
		// 이 때, 첫 번째 숫자가 0이면 0이 아닌 숫자가 나올 때까지 0을 버림
		List<Long> list = new ArrayList<>();
		for(int i=0; i<n; i++) {
			String str = "";
			boolean removeZero = false;
			for(int j=array[i].length()-1; j>=0; j--) {				
				if(!removeZero && array[i].charAt(j) == 0) {
					continue;
				} else {
					str += array[i].charAt(j);
					removeZero = true;
				}
			}
			if(str == "") {
				list.add((long)0);
			} else {
				list.add(Long.parseLong(str));
			}
		}
		Collections.sort(list);
		for(Long a : list) {
			bw.write(a + "\n");
		}
		bw.close();
	}
}
