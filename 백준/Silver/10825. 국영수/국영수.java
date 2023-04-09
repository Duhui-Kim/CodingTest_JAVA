import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Student implements Comparable<Student> {
        String n;
        int g, y, s;
        private Student(String name, int gook, int young, int su) {
            this.n = name;
            this.g = gook;
            this.y = young;
            this.s = su;
        }
        @Override
        public String toString() {
            return n + "\n";
        }

        @Override
        public int compareTo(Student o) {
            if (g != o.g) return o.g - g;
            if (y != o.y) return y - o.y;
            if (s != o.s) return o.s - s;
            return n.compareTo(o.n);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Student[] arr = new Student[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Student(st.nextToken(), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(arr);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(arr[i].toString());
        }
        System.out.println(sb);
    }
}