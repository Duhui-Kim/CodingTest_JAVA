import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		// 마리오 점수를 저장해줄 변수를 선언한다.
		double mario = 0;
		
		for(int i=0; i<10; i++) {
			double point = sc.nextInt();
			// 100과 마리오의 점수차가 100과 마리오+입력포인트의 점수차보다 크면 더해줌
			if(Math.abs(100 - mario) > Math.abs(100 - mario - point)) {
				mario += point;
			// 같을 경우 포인트를 더해주고 종료
			} else if(Math.abs(100 - mario) == Math.abs(100 - mario - point)) {
				mario += point;
				break;
			// 마리오의 현재 점수가 가장 100과 가까우므로 종료
			} else {
				break;
			}
		}
		System.out.println((int)mario);
	}
}
