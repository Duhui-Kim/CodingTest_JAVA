import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		if(N == 0) {
			System.out.println(0);
			return;
		}
		
		List<Double> arr = new ArrayList<>();
		
		for(int i=0; i<N; i++) {
			arr.add(Double.parseDouble(br.readLine()));
		}
		
		Collections.sort(arr);;
		
		int remove = (int) Math.round(N * 0.15);
		double sum = 0;
		
		for(int i=remove; i<N-remove; i++) {
			sum += arr.get(i);
		}
		
		sum = sum / ((double) N- (2*remove));
		
		System.out.println(Math.round(sum));
	}
}