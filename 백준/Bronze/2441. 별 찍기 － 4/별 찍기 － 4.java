import java.util.*;
import java.io.*;

public class Main{
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		for(int j=0; j<N; j++) {
			for(int i=N-j; i<N; i++) {
				System.out.print(" ");
			}
			for(int i=j; i<N; i++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}