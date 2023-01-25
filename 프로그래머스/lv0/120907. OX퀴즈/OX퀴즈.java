class Solution {
    public String[] solution(String[] quiz) {
        for(int i=0; i<quiz.length; i++) {
			if(quiz[i].contains("+")) {
				if(Integer.parseInt(quiz[i].split(" ")[0]) + Integer.parseInt(quiz[i].split(" ")[2]) == Integer.parseInt(quiz[i].split(" ")[4])){
					quiz[i] = "O";
				} else {
					quiz[i] = "X";
				}
			} else {
				if(Integer.parseInt(quiz[i].split(" ")[0]) - Integer.parseInt(quiz[i].split(" ")[2]) == Integer.parseInt(quiz[i].split(" ")[4])){
					quiz[i] = "O";
				} else {
					quiz[i] = "X";
				}
			}
		}
        return quiz;
    }
}