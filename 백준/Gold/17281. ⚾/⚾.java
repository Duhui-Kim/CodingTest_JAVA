import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		int[][] teams = new int[N][9];
				
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				teams[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
		int maxPoint = 0;		
		
		do {
			int idx = 0;
			int point = 0;
			
			for(int i=0; i<N; i++) {
				int outCnt = 0;
				boolean[] check = new boolean[3];

				while(outCnt < 3) {
					int cur = 0;
					
					if(idx == 3) {
						cur = 0;
					} else if(idx > 3) {
						cur = arr[idx-1];
					} else {
						cur = arr[idx];						
					}
					
					int hit = teams[i][cur];
					
					if(hit == 0) {
						outCnt++;
						idx++;
					} else {
						point += getPoint(check, hit);
						idx++;
					}
					idx %= 9;
				}
			}
			maxPoint = Math.max(maxPoint, point);
		} while(nextPermutation(arr));
		
		System.out.println(maxPoint);
	}

	private static int getPoint(boolean[] check, int hit) {
		int point = 0;
		if(check[2]) point++;
		
		switch(hit) {
		case 1:
			check[2] = check[1];
			check[1] = check[0];
			check[0] = true;
			break;
		case 2:
			if(check[1]) point++;
			check[2] = check[0];
			check[1] = true;
			check[0] = false;
			break;
		case 3:
			if(check[1]) point++;
			if(check[0]) point++;
			check[2] = true;
			check[1] = false;
			check[0] = false;
			break;
		case 4:
			if(check[1]) point++;
			if(check[0]) point++;
			point++;
			
			Arrays.fill(check, false);			
			break;
		}
		
		return point;
	}

	private static boolean nextPermutation(int[] arr) {
		int i = arr.length - 2;
		
		while(i >= 0 && arr[i] > arr[i+1]) i--;
		
		if(i < 0) return false;
		
		int j = arr.length - 1;
		
		while(j > i && arr[j] < arr[i]) j--;
		
		swap(arr, i, j);
		
		int a = i+1;
		int b = arr.length - 1;
		
		while(a < b) {
			swap(arr, a, b);
			a++;
			b--;
		}
			
		return true;
	}
	
	private static void swap(int[] arr, int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}
}