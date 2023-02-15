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
		
		for(int t=0; t<10; t++) {
			// testCase값 받아오기
			int testCase = sc.nextInt();
			
			// 배열의 합을 저장해둘 202 크기의 배열을 만든다.
			int[] sumList = new int[202];
			
			// 행의 합은 0~99에 저장, 100~199에는 행의 합 저장 200에는 / 대각선의 합 저장, 201에는 \ 대각선의 합을 저장하여 최대값을 찾는다.
			for(int i=0; i<100; i++) {
				for(int j=0; j<100; j++) {
					int num = sc.nextInt();
					// 행의 합은 (x, y)에서 x가 고정되었을 때의 합이므로 x에 따라 인덱스가 늘어남
					sumList[i] += num;
					// 열의 합은 (x, y)에서 y가 고정되었을 때의 합이므로 100+y에 따라 인덱스가 늘어남
					sumList[100+j] += num;
					// /의 합은 (x, y)에서 x+y가 99일 때의 합으로 200에 저장
					if(i+j == 99) sumList[200] += num;					
					// \의 합은 (x, y)에서  x==y일 때의 합으로 201에 저장
					if(i==j) sumList[201] += num;
				}
			}
			int max = 0;
			for(int i=0; i<sumList.length; i++) {
				if(sumList[i] > max) {
					max = sumList[i];
				}
			}
			sb.append("#" + testCase + " " + max + "\n");
		}
		System.out.println(sb);
	}
}