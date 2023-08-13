import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0) {
            return cities.length * 5;
        }
        
        Set<String> set = new HashSet<>();
        Map<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<cities.length; i++) {
            cities[i] = cities[i].toLowerCase();
            
            if(set.size() < cacheSize) {
                if(set.contains(cities[i])) {
                    answer += 1;
                } else {
                    set.add(cities[i]);
                    answer += 5;    
                }
            } else {
                if(set.contains(cities[i])) {
                    answer += 1;
                } else {
                    answer += 5;
                    
                    int min = 1000000;
                    String s = "";
                    
                    Iterator<String> iter = set.iterator();
                    while(iter.hasNext()) {
                        String nxt = iter.next();
                        int idx = map.get(nxt);
                        
                        if(min > idx) {
                            min = idx;
                            s = nxt;
                        }
                    }
                    set.remove(s);
                    set.add(cities[i]);
                }
            }
            map.put(cities[i], i);
        }
                
        return answer;
    }
}