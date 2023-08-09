class Solution {
    public int solution(String s) {
        StringBuilder sb = new StringBuilder();        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(c - '0' >= 0 && c - '0' <= 9) {
                sb.append(c);
                continue;
            }
            
            switch(c) {
                case 'z': // zero
                    sb.append("0");
                    i += 3;
                    break;
                case 'o': // one
                    sb.append("1");
                    i += 2;
                    break;
                case 't': // two, three
                    if(s.charAt(i+1) == 'w') {
                        sb.append("2");
                        i += 2;
                    } else {
                        sb.append("3");
                        i += 4;
                    }
                    break;
                case 'f': // four, five
                    if(s.charAt(i+1) == 'o') {
                        sb.append("4");
                        i += 3;
                    } else {
                        sb.append("5");
                        i += 3;
                    }
                    break;
                case 's': // six, seven
                    if(s.charAt(i+1) == 'i') {
                        sb.append("6");
                        i += 2;
                    } else {
                        sb.append("7");
                        i += 4;
                    }
                    break;
                case 'e': // eight
                    sb.append("8");
                    i += 4;
                    break;
                case 'n': // nine;
                    sb.append("9");
                    i += 3;
                    break;
            }
        }
        
        int answer = Integer.parseInt(sb.toString());
        return answer;
    }
}