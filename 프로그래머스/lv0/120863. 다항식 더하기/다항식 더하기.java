class Solution {
    public String solution(String polynomial) {
        String answer;
		
		int a = 0;
		int b = 0;
		String[] abc = polynomial.split(" ");
		for(int i=0; i<abc.length; i+=2) {
			if(abc[i].contains("x")) {
				if(abc[i].equals("x")) {
					a++;
				} else {
					a+= Integer.parseInt(abc[i].replace("x", ""));
				}
			} else {
				b += Integer.parseInt(abc[i]);
			}
		}
		if(b == 0) {
			if( a == 0) {
				answer = "0";
			} else if (a == 1) {
				answer = "x";
			} else {
				answer = a+"x";
			}
		} else {
			if(a == 0) {
				answer = b+"";
			} else if (a == 1) {
				answer = "x + "+b;
			} else {
				answer = a+"x + "+b;
			}
		}
        return answer;
    }
}