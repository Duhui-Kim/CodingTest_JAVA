import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<String, String> history = new HashMap<>();
        Map<String, Integer> totalTime = new HashMap<>();
        
        for(String record : records) {
            String[] datas = record.split(" ");
            
            if(datas[2].equals("IN")) {
                history.put(datas[1], datas[0]);
            } else {
                String inTime = history.get(datas[1]);
                history.remove(datas[1]);
                
                int inHour = Integer.parseInt(inTime.split(":")[0]);
                int inMinute = Integer.parseInt(inTime.split(":")[1]);
                
                int outHour = Integer.parseInt(datas[0].split(":")[0]);
                int outMinute = Integer.parseInt(datas[0].split(":")[1]);
                
                int parkingTime = (outHour - inHour) * 60 + (outMinute - inMinute);
                
                if(totalTime.containsKey(datas[1])) {
                    parkingTime += totalTime.get(datas[1]);
                }
                
                totalTime.put(datas[1], parkingTime);
            }
        }
        
        for(Map.Entry<String, String> entity : history.entrySet()) {
            String number = entity.getKey();
            String inTime = entity.getValue();
            
            int inHour = Integer.parseInt(inTime.split(":")[0]);
            int inMinute = Integer.parseInt(inTime.split(":")[1]);
            
            int outHour = 23;
            int outMinute = 59;
            
            int parkingTime = (outHour - inHour) * 60 + (outMinute - inMinute);
                
            if(totalTime.containsKey(number)) {
                parkingTime += totalTime.get(number);
            }

            totalTime.put(number, parkingTime);
        }
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[0] - o2[0];
        });
        
        for(Map.Entry<String, Integer> entity : totalTime.entrySet()) {
            queue.offer(new int[] {Integer.parseInt(entity.getKey()), entity.getValue()});
        }
        
        int[] answer = new int[queue.size()];
        
        for(int i=0; i<answer.length; i++) {
            int time = queue.poll()[1];
            answer[i] = fees[1];
            
            if(time <= fees[0]) {
                continue;
            } else {
                time -= fees[0];    
            }
            
            answer[i] += time % fees[2] == 0 ? time / fees[2] * fees[3] : (time / fees[2] + 1) * fees[3];
        }
        
        return answer;
    }
}