import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        
        // 크로아티아 문자가 있을 경우 문자를 .으로 치환시킨 뒤 출력한다.
        str = str.replace("c=", ".");
        str = str.replace("c-", ".");
        str = str.replace("dz=", ".");
        str = str.replace("d-", ".");
        str = str.replace("lj", ".");
        str = str.replace("nj", ".");
        str = str.replace("s=", ".");
        str = str.replace("z=", ".");

        System.out.println(str.length());
    }
}
