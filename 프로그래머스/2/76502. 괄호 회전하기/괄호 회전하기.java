import java.util.*;

class Solution {        
    public int solution(String s) {
        Queue<Character> queue = new LinkedList<>();
        ArrayDeque<Character> stack = new ArrayDeque<>();
        Map<Character, Character> pair = new HashMap<>();
        pair.put('[', ']');
        pair.put('(', ')');
        pair.put('{', '}');
        
        int size = s.length();
        
        for (int i=0; i<size; i++) {
            queue.offer(s.charAt(i));
        }
        
        int answer = 0;
        
        for (int i=0; i<size; i++) {
            answer += calculate(queue, stack, pair, size);
            queue.offer(queue.poll());
            stack.clear();
        }
        
        return answer;
    }
    
    private int calculate(Queue<Character> queue, ArrayDeque<Character> stack, Map<Character, Character> pair, int size) {
        boolean check = false;
        
        for (int i=0; i<size; i++) {
            char next = queue.poll();
            queue.offer(next);
            
            if (check) continue;
            
            if (pair.containsKey(next)) {
                stack.offerLast(next);
            } else {
                if (stack.isEmpty() || next != pair.get(stack.peekLast())) {
                    check = true;
                } else {
                    stack.pollLast();
                }
            }
        }
        
        if (!check && stack.isEmpty()) {
            return 1;
        }
        return 0;
    }
}