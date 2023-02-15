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
		
		int testCase = sc.nextInt();
		
		for(int t=1; t<=testCase; t++) {
			// 배열의 크기 N과 단어의 크기 K 입력받기
			int N = sc.nextInt();
			int K = sc.nextInt();
			
			int[][] puzzle = new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					puzzle[i][j] = sc.nextInt();
				}
			}
			
			// 중복을 피하기 위해 세로체크용 boolean 배열과 가로체크용 boolean 배열을 만든다
			boolean[][] Hcheck = new boolean[N][N];
			boolean[][] Vcheck = new boolean[N][N];
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 2중 for문을 통해 탐색을 하다가 1을 만났을 때 ,
					if(puzzle[i][j] == 1) {
						// 가로 boolean 배열에 표시가 되어있지 않으면 
						// 0을 만나기 전까지 가로로 뻗어나가며 가로 boolean 배열에 방문표시를 한다. 
						// Hcnt == K이면 cnt++ / 0을 만나면 Hcnt 1로 초기화
						int Hcnt = 0;
						if(!Hcheck[i][j]) {
							int y=0;
							while(j+y<N && puzzle[i][j+y] == 1) {
								Hcheck[i][j+y] = true;
								Hcnt++;
								y++;
							}
							if(Hcnt == K) cnt++;
						}					
						// 세로 boolean 배열에 표시가 되어있지 않으면 
						// 세로로도 뻗어나가며 세로 boolean 배열에 방문표시를 한다. 
						// Vcnt == K이면 cnt++ / 0을 만나면 Vcnt 1로 초기화
						int Vcnt = 0;
						if(!Vcheck[i][j]) {
							int x=0;
							while(i+x<N && puzzle[i+x][j] == 1) {
								Vcheck[i+x][j] = true;
								Vcnt++;
								x++;
							}
							if(Vcnt == K) cnt++;
						}
					}
				}
			}
			sb.append("#" + t + " " + cnt + "\n");
		}
		System.out.println(sb);
	}
}