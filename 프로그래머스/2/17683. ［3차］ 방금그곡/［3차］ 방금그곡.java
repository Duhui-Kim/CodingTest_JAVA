import java.util.*;

class Solution {
    private int playTime;
    private String NONE = "(None)";
    
    public String solution(String m, String[] musicinfos) {
        String answer = NONE;
        playTime = -1;
        
        for (int i=0; i<musicinfos.length; i++) {
            StringTokenizer st = new StringTokenizer(musicinfos[i], ",");
            String ret = findSong(m, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
            
            if (!ret.equals(NONE)) {
                answer = ret;
            }
        }
                
        return answer;
    }
    
    private String findSong(String m, String start, String end, String title, String melody) {        
        int gap = parseTime(end) - parseTime(start);
        
        if (playTime >= gap) {
            return NONE;
        }
        
        m = replace(m);
        melody = replace(melody);
        
        int melodySize = melody.length();
        
        String retMelody = "";
        if (gap <= melodySize) {
            retMelody = melody.substring(0, gap);
        } else {
            for (int i=0; i<gap/melodySize; i++) {
                retMelody += melody;
            }
            retMelody += melody.substring(0, gap%melodySize);
        }
        
        if (retMelody.contains(m)) {
            playTime = gap;
            return title;
        }
        return NONE;
    }
    
    private int parseTime(String time) {
        StringTokenizer st = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(st.nextToken());
        int minute = Integer.parseInt(st.nextToken());
        
        return hour * 60 + minute;
    }
    
    private String replace(String melody) {
        return melody.replace("C#", "c")
            .replace("D#", "d")
            .replace("F#", "f")
            .replace("G#", "g")
            .replace("A#", "a")
            .replace("B#", "b");
    }
}