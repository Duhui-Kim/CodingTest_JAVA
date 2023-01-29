import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
	// merge와 devide 부분을 클래스로 나눠서 만들었기 때문에
	// K와 cnt, num을 전역변수로 만듦
	private static int[] list, tmp;
	private static int K;
	private static int cnt=0;
	private static int num = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 배열의 크기 N과 구해야하는 K번째 값인 
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 배열에 값을 넣어줌
		st = new StringTokenizer(br.readLine());
		
		list = new int[N];
		for(int i=0; i<N; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		tmp = new int[list.length];
		// num값 출력
		mergeSort(list, 0, N-1);
		System.out.println(num);
	}
	
	// list 크기가 1이 될 때까지 반으로 나눈 뒤,
	// merge 시작
	private static void mergeSort(int[] list, int start, int end) {
		if(cnt>K) return;
		if(start<end) {
			int mid = (start+end)/2;
			mergeSort(list, start, mid);
			mergeSort(list, mid+1, end);
			
			merge(list, start, mid, end);
		}
	}
	
	private static void merge(int[] list, int start, int mid, int end) {
		// 왼쪽 배열의 시작점을 q,
		// 오른쪽 배열의 시작점을 p로 명명
		int idx = 0;
		int q = start;
		int p = mid+1;
		
		// 왼쪽 리스트의 인수와 오른쪽 리스트의 인수를 차례로 비교하여
		// 작은 값을 list에 넣어줌
		while(q<=mid && p <= end) {
			if(list[q] <= list[p]) {
				tmp[idx] = list[q++];
			} else {
				tmp[idx] = list[p++];
			}
			idx++;
		}
		
		// 왼쪽이나 오른쪽 리스트가 모두 들어갔을 경우,
		// 나머지 리스트의 값을 입력해줌
		while(q<=mid) {
			tmp[idx++] = list[q++];
		}
		while(p<=end) {
			tmp[idx++] = list[p++];
		}
		
		q = start;
		idx = 0;
		while(q<=end) {
			cnt++;
			if(cnt == K) {
				num = tmp[idx];
				return;
			}
			list[q++] = tmp[idx++];
		}
	}
}