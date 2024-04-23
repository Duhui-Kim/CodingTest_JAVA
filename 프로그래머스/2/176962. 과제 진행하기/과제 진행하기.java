import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Homework> newHomework = new PriorityQueue<>((o1, o2) -> {
            return o1.start - o2.start;
        });
        
        Stack<Homework> oldHomework = new Stack<>();
        
        for (String[] plan : plans) {
            newHomework.offer(new Homework(plan[0], parseTime(plan[1]), Integer.parseInt(plan[2])));
        }
                
        String[] answer = new String[plans.length];
        
        int idx = 0;
        while(!newHomework.isEmpty()) {
            Homework curr = newHomework.poll();
            
            if (newHomework.isEmpty()) {
                answer[idx++] = curr.name;
                continue;
            }
            
            Homework next = newHomework.peek();
            
            int progress = next.start - curr.start;
            
            if (progress >= curr.remain) {
                answer[idx++] = curr.name;
                progress -= curr.remain;
                
                while (!oldHomework.isEmpty() && progress > 0) {
                    if (oldHomework.peek().remain <= progress) {
                        Homework remainHomework = oldHomework.pop();
                        
                        progress -= remainHomework.remain;
                        answer[idx++] = remainHomework.name;
                    } else {
                        oldHomework.peek().remain -= progress;
                        progress = 0;
                    }
                }
            } else {
                curr.remain -= progress;
                oldHomework.push(curr);
            }
        }
        
        while(!oldHomework.isEmpty()) {
            answer[idx++] = oldHomework.pop().name;
        }
        
        return answer;
    }
    
    private int parseTime(String time) {
        String hour = time.split(":")[0];
        String minute = time.split(":")[1];
        
        return Integer.parseInt(hour) * 60 + Integer.parseInt(minute);
    }
    
    private class Homework {
        String name;
        int start;
        int remain;
        
        public Homework(String name, int start, int remain) {
            this.name = name;
            this.start = start;
            this.remain = remain;
        }
    }
}