import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public long[] solution(long k, long[] room_number) {
        			
		HashMap<Long, Long> map = new HashMap<>(room_number.length);
		for(int i=0; i<room_number.length; i++) {
			room_number[i] = roomNum(room_number[i]);
		}
        return room_number;
    }
   	// long 타입을 인덱스로 받으려고 맵을 썼음
	private static HashMap<Long, Long> map = new HashMap<>();
	
	private static long roomNum(long a) {
		
		// 해쉬맵에서 a를 키값으로 넣었을 때 value가 없으면 a를 키에 넣어주고
		// a를 리턴함
		if(map.get(a) == null) {
			map.put(a, a+1);
			return a;
			
		// value가 있으면 a+1해주고 반복
		} else {
			map.replace(a, roomNum(map.get(a)));
			return map.get(a);
		}
	}

}