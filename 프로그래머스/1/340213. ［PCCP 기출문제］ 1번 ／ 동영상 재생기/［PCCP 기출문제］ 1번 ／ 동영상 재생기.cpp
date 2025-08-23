#include <string>
#include <vector>

using namespace std;

int convert(string& time) {
    int minute = stoi(time.substr(0, 2));
    int second = stoi(time.substr(3, 2));
    
    return minute * 60 + second;
}

string convert(int num) {
    string minute = num / 60 < 10 ? "0" : "";
    string second = num % 60 < 10 ? "0" : "";

    minute += to_string(num / 60);
    second += to_string(num % 60);
    
    return minute + ":" + second;
}

string solution(string video_len, string pos, string op_start, string op_end, vector<string> commands) {   
    int int_video_len = convert(video_len);
    int int_pos = convert(pos);
    int int_op_start = convert(op_start);
    int int_op_end = convert(op_end);
    
    for (string cmd : commands) {
        if (int_op_start <= int_pos && int_pos <= int_op_end) {
            int_pos = int_op_end;
        }
        
        if (cmd == "next") {
            int_pos += 10;

            if (int_pos > int_video_len) {
                int_pos = int_video_len;
            }
        } else {
            int_pos -= 10;

            if (int_pos < 0) {
                int_pos = 0;
            }
        }
    }
    
    if (int_op_start <= int_pos && int_pos <= int_op_end) {
        int_pos = int_op_end;
    }
    
    
    return convert(int_pos);
}