import java.util.*;
import java.io.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {        
        Map<String, People> friendsMap = new HashMap<>();
        
        for(String name : friends) {
            friendsMap.put(name, new People(name));
        }
        
        for(String history : gifts) {
            StringTokenizer st = new StringTokenizer(history);
            
            People giver = friendsMap.get(st.nextToken());
            People receiver = friendsMap.get(st.nextToken());
            
            if (giver.giveList.get(receiver.name) == null) {
                giver.giveList.put(receiver.name, 1);
            } else {
                giver.giveList.put(receiver.name, giver.giveList.get(receiver.name) + 1);
            }
            giver.give++;
            receiver.receive++;
        }
        
        int size = friends.length;
        
        for(int i=0; i<size-1; i++) {
            for(int j=i+1; j<size; j++) {
                People first = friendsMap.get(friends[i]);
                People second = friendsMap.get(friends[j]);
                
                int firstGive = first.giveList.get(second.name) == null ? 0 : first.giveList.get(second.name);
                int secondGive = second.giveList.get(first.name) == null ? 0 : second.giveList.get(first.name);
                
                if (firstGive == secondGive) {
                    int firstPoint = first.give - first.receive;
                    int secondPoint = second.give - second.receive;
                    
                    if (firstPoint == secondPoint) {
                        continue;
                    } else if (firstPoint > secondPoint) {
                        first.willReceive++;
                    } else {
                        second.willReceive++;
                    }
                } else if (firstGive > secondGive) {
                    first.willReceive++;
                } else {
                    second.willReceive++;
                }
            }
        }
        
        int answer = 0;
        for (String name : friends) {
            answer = answer > friendsMap.get(name).willReceive ? answer : friendsMap.get(name).willReceive;
        }
        
        return answer;
    }
    
    private class People {
        int give;
        int receive;
        String name;
        
        int willReceive;
        
        Map<String, Integer> giveList = new HashMap<>();
        
        public People(String name) {
            this.name = name;
        }
    }
}