import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 1. 단어의 개수 N을 받고 리스트에 문자열을 받는다.
		int N = Integer.parseInt(br.readLine());
		List<String> list = new ArrayList<>(N);
		
		for(int i=0; i<N; i++) {
			list.add(br.readLine());
		}
		
		// 2. <문자열크기, 문자열리스트>을 각각 key, value로 받는 map을 만든다.
		Map<Integer, List<String>> map = new HashMap<>();
		
		
		for(int i=0; i<list.size(); i++) {
			// 3. 같은 문자열 크기를 갖는 값들을 value안의 리스트 안에 넣는다.
			// 3-1. key가 존재하지 않으면 새롭게 리스트를 만들어서 넣어주고
			if(map.get(list.get(i).length()) == null) {
				List<String> newlist = new ArrayList<>();
				newlist.add(list.get(i));
				map.put(list.get(i).length(), newlist);
			// 3-2. 이전에 이미 같은 key값이 있었다면,
				// 중복을 체크한 뒤 중복이 없을 경우 해당 value의 list에 추가한다.
			} else {
				if(!map.get(list.get(i).length()).contains(list.get(i))) {
					map.get(list.get(i).length()).add(list.get(i));
				}
			}
		}
		
		// 4. 1~50 중 key 값이 있는 경우에 value를 호출하고, value를 정렬하여 출력한다.
		for(int i=0; i<51; i++) {
			if(map.get(i) != null) {
				Collections.sort(map.get(i));
				for(String s : map.get(i)) {
					bw.write(s+"\n");
				}
			}
		}
		bw.close();
	}
}
