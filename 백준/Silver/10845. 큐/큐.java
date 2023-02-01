import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		List<Integer> list = new ArrayList<>();
		
		
		int n = Integer.parseInt(br.readLine());
		
		String cmd = "";
		for(int i=0; i<n; i++) {
			// cmd를 읽어와 바로바로 명령 실행
			cmd = br.readLine();
			
			// push의 경우 cmd를 공백으로 나눠서 두 번째 숫자를 리스트에 삽입
			if(cmd.contains("push")) {
				list.add(0, Integer.parseInt(cmd.split(" ")[1]));
				
			// front의 경우 맨 앞이 비어있을 경우 -1 출력
			// 아닐 경우 해당 숫자 출력
			} else if (cmd.equals("front")) {
				if(list.isEmpty()) {
					bw.write(-1+"\n");
				} else {
					bw.write(list.get(list.size()-1)+"\n");
				}
			// back의 경우 맨 뒤가 비어있을 경우 -1 출력
			// 아닐 경우 해당 숫자 출력
			} else if (cmd.equals("back")) {
				if(list.isEmpty()) {
					bw.write(-1+"\n");
				} else {
					bw.write(list.get(0)+"\n");
				}
			// list의 크기 출력	
			} else if(cmd.equals("size")) {
				bw.write(list.size()+"\n");
			// empty일 경우 1, 아닐 경우 0	
			} else if(cmd.equals("empty")) {
				if(list.isEmpty()) {
					bw.write(1+"\n");
				} else {
					bw.write(0+"\n");
				}
			} else {
				if(list.isEmpty()) {
					bw.write(-1+"\n");
				} else {
					bw.write(list.get(list.size()-1)+"\n");
					list.remove(list.size()-1);
				}
			}
		}
		bw.close();
	}
}

