
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int test = Integer.parseInt(br.readLine());
		int reCnt = 0;
		
		for(int i=0; i<test; i++) {
			String str = br.readLine();
			bw.write(isPalindrome(str) + " " + isPalindrome(str, reCnt) + "\n");
		}
		bw.flush();
		bw.close();
	}
	public static int recursion(char[] s, int l, int r){
	    if(l >= r) return 1;
	    else if(s[l] != s[r]) return 0;
	    else return recursion(s, l+1, r-1);
	}
	
	public static int recursion(char[] s, int l, int r, int reCnt){
		reCnt++;
	    if(l >= r) return reCnt;
	    else if(s[l] != s[r]) return reCnt;
	    else return recursion(s, l+1, r-1, reCnt);
	}
	
	public static int isPalindrome(String str){
		char[] s = str.toCharArray();
	    return recursion(s, 0, s.length-1);
	}
	
	public static int isPalindrome(String str, int reCnt){
		char[] s = str.toCharArray();
	    return recursion(s, 0, s.length-1, reCnt);
	}

}
