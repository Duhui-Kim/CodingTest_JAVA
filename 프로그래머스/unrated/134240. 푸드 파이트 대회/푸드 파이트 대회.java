import java.util.*;
import java.io.*;

class Solution {
    public String solution(int[] food) {
        StringBuilder sb = new StringBuilder();
        ArrayDeque<String> queue = new ArrayDeque<>();
        
        for(int i=1; i<food.length; i++) {
            for(int j=0; j<food[i]/2; j++) {
                sb.append(i);
                queue.offerFirst("" + i);
            }
        }
        sb.append("0");
        
        while(!queue.isEmpty()) {
            sb.append(queue.pollFirst());
        }
        
        return sb.toString();
    }
}