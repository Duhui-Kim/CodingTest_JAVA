import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		for(int m=1; m<=10; m++) {
			int dump = sc.nextInt();
			
			int[] arr = new int[100];
			
			for(int i=0; i<100; i++) {
				arr[i] = sc.nextInt();
			}
			
			int cnt=0;
			while(dump > cnt) {
				int maxIdx = 0;
				int minIdx = 0;
				for(int j=0; j<100; j++) {
					if(arr[j] > arr[maxIdx]) {
						maxIdx = j;
					}
					if(arr[j] < arr[minIdx]) {
						minIdx = j;
					}
				}
				arr[maxIdx]--;
				arr[minIdx]++;
				cnt++;
			}
			int max = Integer.MIN_VALUE;
			int min = Integer.MAX_VALUE;
			
			for(int j=0; j<100; j++) {
				if(arr[j] >= max) {
					max = arr[j];
				}
				if(arr[j] <= min) {
					min = arr[j];
				}
			}
			sb.append("#" + m + " " + (max - min) + "\n");
		}
		System.out.println(sb);
	}
}
