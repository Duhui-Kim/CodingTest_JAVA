/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.FileInputStream;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		// 도착점은 하나이지만, 출발점은 여러 개이다.
		// 여러 개의 출발점에서 다 출발해보는 것은 불필요하다고 생각해서, 도착점에서부터 거슬러올라가는 방법을 택했다.
		
		for(int t=1; t<=10; t++) {
			int testCase = sc.nextInt();
			// 배열을 입력받고, 2는 항상 맨 아래 행에 있으므로, 맨 아래 행에서 2를 찾는다.
			int N = 100;
			int s = 0;
			int[][] ladder = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					ladder[i][j] = sc.nextInt();
					if(ladder[i][j] == 2) s = j;
				}
			}
			
			// 반복문을 실행하여 x 값이 0이 될 때까지 진행하는데,
			int x = N-1;
			while(x>0) {
				// 좌측으로 이동했을 경우 다시 우측으로 이동되지 않게 boolean을 선언한다.
				boolean isShift = false;
				// 맨 아래 행과 맨 위의 행에는 좌우 이동이 없다는 점을 생각하여 
				// x-1 후 좌우로 1이 있는지 체크한다.
				x--;
				// 좌측에 1이 있다면 0이 나올 때까지 좌측으로 이동
				while(s-1>=0 && s-1<N && ladder[x][s-1] == 1) {
					s--;
					isShift = true;
				}
				// 좌측으로 이동하지 않았고, 우측에 1이 있다면 0이 나올 때까지 우측으로 이동
				while(!isShift && s+1>=0 && s+1<N && ladder[x][s+1] == 1) s++;
			}
			sb.append("#" + testCase + " " + s + "\n");
		}
		sc.close();
		System.out.println(sb);
	}
}