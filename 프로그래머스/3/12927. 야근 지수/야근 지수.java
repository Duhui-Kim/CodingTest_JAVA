import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            queue.offer(work);
        }
        
        while (n-- > 0) {
            int next = queue.poll();
            if (next == 0) break;
            
            queue.offer(next - 1);
        }
    
        long answer = 0;
        
        while (!queue.isEmpty()) {
            int next = queue.poll();
            answer += (long) next * next;
        }        
        
        return answer;
    }
}