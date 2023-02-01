import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int n = Integer.parseInt(br.readLine());
		
		int[] newlist = new int[2000001];

		for(int i=0; i<n; i++) {
			newlist[Integer.parseInt(br.readLine())+1000000] += 1;
		}
		
		for(int i=0; i<2000001; i++) {
			if(newlist[i] != 0) {
				for(int j=0; j<newlist[i]; j++) {
					bw.write((i-1000000)+"\n");
				}
			}
		}
		
//		ArrayList<Integer> al = new ArrayList<>(n);
//
//		for(int i=0; i<n; i++) {
//			al.add(Integer.parseInt(br.readLine()));
//		}
//		Collections.sort(al);
//		
//		for(int i=0; i<n; i++) {
//			bw.write(al.get(i)+"\n");
//		}
		bw.close();
	}
}
