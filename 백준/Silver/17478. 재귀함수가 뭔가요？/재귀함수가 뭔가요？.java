import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		int m = n;
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		repeater("", m);
		
	}
    
    // 반복횟수 지정을 위한 변수 선언
	private static int n;
	
	private static void repeater(String a, int m) {
        // 종료 조건 선언
		if(m==0) {
			a += "____";
			System.out.println(a + "\"재귀함수가 뭔가요?\"");
			System.out.println(a + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
			System.out.println(a + "라고 답변하였지.");			
			return;
		}
        
        // 처음에는 밑줄이 추가되지 않으므로 m==n 일 경우에는 a는 아무것도 없음
		if (m<n) a += "____";
		
        // 프린트를 진행
		System.out.println(a + "\"재귀함수가 뭔가요?\"");
		System.out.println(a + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.");
		System.out.println(a + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.");
		System.out.println(a + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
		
        // m을 하나 줄여서 재귀 시작
		repeater(a, m-1);
		
        // 재귀가 완료되면 가장 안쪽부터 마지막 말 출력하며 빠져나옴
		System.out.println(a + "라고 답변하였지.");
	}
}
