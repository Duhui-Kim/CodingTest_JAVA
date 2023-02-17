import java.util.Scanner;

public class Solution {
	private static char[][] map;
	private static int size;
	private static int max;	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		
		size = 100;

		for(int t=0; t<10; t++) {
			int testNum = sc.nextInt();
			
			// 100 * 100 배열을 만든다.
			map = new char[size][size];
			
			// 배열에 값을 입력한다.
			for(int i=0; i<size; i++) {
				map[i] = sc.next().toCharArray();
			}
			
			// max값을 0으로 초기화한다.
			max = 0;
			
			// 반복문을 돌며 좌표값을 받아오는데,
			// max 값이 예를 들어 10일 경우, 90번째 이후의 값은 측정할 필요가 없으므로 제한을 두었다.
			for(int i=0; i<size; i++) {
				for(int j=0; j<size; j++) {
					// 가로측정은 j값이 size-max 미만일 때만
					if(j<size-max) {
						checkRow(i, j);
					}
					// 세로측정은 i값이 size-max 미만일 때만
					if(i<size-max) {
						checkCol(i, j);
					}
				}
			}
			sb.append("#" + testNum + " " + max + "\n");
		}
		System.out.println(sb);
	}
	
	private static void checkRow(int x, int y) {
		// 초기값 세팅
		int cnt = 0;
		int start = y;
		int end = size-1;
		int i = 1;

		// 맨 뒤 - 시작점의 값이 max 이상일 경우에만 진행
		while(size - i - y > max) {
			// 다른 숫자가 있으면 cnt 초기화
			// 시작점도 초기화
			// 맨 뒤는 1칸 당겨옴
			if(map[x][start] != map[x][end]) {
				cnt = -2;
				i++;
				start = y-1;
				end = size-i;
			}
			// 시작점과 끝점을 비교하며 거리를 좁힘
			// 2개씩 이동하므로 cnt는 2씩 증가
			cnt+=2;
			start++;
			end--;
			
			// 홀수일 경우 가운데에 한 자리가 남으므로 cnt를 1 더해주고 종료
			if(end == start) {
				cnt++; 
				break;
				
			// 짝수였을 경우 두 수가 교차되는 지점에서 종료되므로 cnt를 더해주지 않고 종료
			} else if(end < start) {
				break;
			}
		}
		if(cnt > max) {
			max = cnt;
		}
	}
	
	private static void checkCol(int x, int y) {
		// 초기값 세팅
		int cnt = 0;
		int start = x;
		int end = size-1;
		int i = 1;

		// 맨 뒤 - 시작점의 값이 max 이상일 경우에만 진행
		while(size - i - x > max) {
			// 다른 숫자가 있으면 cnt 초기화
			// 시작점도 초기화
			// 맨 뒤는 1칸 당겨옴
			if(map[start][y] != map[end][y]) {
				cnt = -2;
				i++;
				start = x-1;
				end = size-i;
			}
			// 시작점과 끝점을 비교하며 거리를 좁힘
			// 2개씩 이동하므로 cnt는 2씩 증가
			cnt+=2;
			start++;
			end--;
			
			// 홀수일 경우 가운데에 한 자리가 남으므로 cnt를 1 더해주고 종료
			if(end == start) {
				cnt++; 
				break;
				
			// 짝수였을 경우 두 수가 교차되는 지점에서 종료되므로 cnt를 더해주지 않고 종료
			} else if(end < start) {
				break;
			}
		}
		if(cnt > max) {
			max = cnt;
		}
	}
	
}
