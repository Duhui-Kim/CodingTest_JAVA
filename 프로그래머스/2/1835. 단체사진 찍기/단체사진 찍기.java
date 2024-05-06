import java.util.*;

class Solution {
    private List<Condition> conditionList = new LinkedList<>();
    private Map<Character, Integer> indexMap = new HashMap<>();
    private char[] people = new char[]{'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    private int answer;
    
    public int solution(int n, String[] data) {
        conditionList.clear();
        indexMap.clear();
        
        for (int i=0; i<people.length; i++) {
            indexMap.put(people[i], i);
        }
        
        for (String d : data) {
            conditionList.add(new Condition(d.charAt(0), d.charAt(2), d.charAt(3), d.charAt(4) - '0'));
        }

        int[] check = new int[people.length];
        answer = 0;
        
        for (int i=0; i<8; i++) {
            check[i] = 1;
            calculate(2, people, check);
            check[i] = 0;
        }
        
        return answer;
    }
    
    private void calculate(int cur, char[] people, int[] check) {
        if (cur > people.length) {
            answer++;
            return;
        }
        
        loop:
        for (int i=0; i<8; i++) {
            if (check[i] != 0) continue;
            
            for (Condition c : conditionList) {
                Character prev = c.contains(people[i]);
                
                if (prev != null) {
                    int prevIdx = check[indexMap.get(prev)];
                    
                    if (!c.satisfy(cur, prevIdx)) {
                        continue loop;
                    }
                }
            }
            
            check[i] = cur;
            calculate(cur+1, people, check);
            check[i] = 0;
        }
    }
    
    private class Condition {
        char n1;
        char n2;
        char compare;
        int value;
        
        public String toString() {
            return n1 + " " + n2 + " " + compare + " " + value;
        }
        
        private Condition(char n1, char n2, char compare, int value) {
            this.n1 = n1;
            this.n2 = n2;
            this.compare = compare;
            this.value = value;
        }
        
        private Character contains(char name) {
            if (name == n1) {
                return n2;
            } else if (name == n2) {
                return n1;
            } else {
                return null;
            }
        }
        
        private boolean satisfy(int a, int b) {
            if (a == 0 || b == 0) return true;
            
            int gap = ((int) Math.abs(a - b)) - 1;
            
            switch(compare) {
                case '<':
                    return gap < value;
                case '>':
                    return gap > value;
                case '=':
                    return gap == value;
            }
            return false;
        }
    }
}