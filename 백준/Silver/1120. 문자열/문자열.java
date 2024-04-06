import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        String first = st.nextToken();
        String second = st.nextToken();
       
        int minCount = first.length();        
        loop:
        for (int i=0; i<second.length() - first.length() + 1; i++) {
            int tempCount = 0;
            
            for (int j=0; j<first.length(); j++) {
                if (second.charAt(i+j) != first.charAt(j)) {
                    tempCount++;
                }
                
                if (tempCount >= minCount) {
                    continue loop;
                }
            }
            
            minCount = tempCount < minCount ? tempCount : minCount;
        }
        
        System.out.println(minCount);
    }
}