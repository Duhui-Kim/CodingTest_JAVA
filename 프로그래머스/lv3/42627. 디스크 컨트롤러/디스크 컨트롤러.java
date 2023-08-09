import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        int size = jobs.length;
        
        Arrays.sort(jobs, (o1, o2) -> {
            if(o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        
        int answer = 0;
        int cur = jobs[0][0];
        int idx = 0;
        
        while(idx < size) {
            for(; idx<size; idx++) {
                if(jobs[idx][0] > cur) break;
                
                queue.offer(jobs[idx]);                
            }            
            int[] arr = queue.poll();

            answer += arr[1] + (cur - arr[0]);
            cur += arr[1];
            
            if(queue.isEmpty() && idx < size) {
                cur = Math.max(cur, jobs[idx][0]);
                queue.offer(jobs[idx]);
                idx++;
            }
        }
        
        while(!queue.isEmpty()) {
            int[] arr = queue.poll();
                                 
            answer += arr[1] + (cur - arr[0]);
            cur += arr[1];
        }
        
        return answer / size;
    }
}