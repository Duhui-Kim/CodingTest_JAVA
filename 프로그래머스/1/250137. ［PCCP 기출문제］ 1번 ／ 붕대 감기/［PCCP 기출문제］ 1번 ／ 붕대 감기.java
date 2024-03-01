class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int attackIdx = 0;
        int time = 0;
        int curHealth = health;
        int healCount = 0;
        
        while(attackIdx < attacks.length) {
            time++;
            
            if (attacks[attackIdx][0] == time) {
                curHealth -= attacks[attackIdx][1];
                
                if(curHealth <= 0) {
                    curHealth = -1;
                    break;
                }
                
                healCount = 0;
                attackIdx++;
                continue;
            }
            
            curHealth += bandage[1];
            healCount++;
            
            if (healCount == bandage[0]) {
                curHealth += bandage[2];
                healCount = 0;
            }
            
            curHealth = curHealth > health ? health : curHealth;
        }
        
        return curHealth;
    }
}