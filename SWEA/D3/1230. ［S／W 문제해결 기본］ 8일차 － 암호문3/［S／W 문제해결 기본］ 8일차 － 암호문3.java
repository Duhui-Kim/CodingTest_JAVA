import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

    static int NODE_MAX = 5000;
    static class Node {
        int data;
        Node next;
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static class LinkedList {
        Node head;
        Node tail;
        Node[] nodeArr;
        int nodeCnt;

        public LinkedList() {
            head = null;
            nodeArr = new Node[NODE_MAX];
            nodeCnt = 0;
        }

        Node getNewNode(int data) {
            nodeArr[nodeCnt] = new Node(data);
            return nodeArr[nodeCnt++];
        }

        void insert(int idx, int[] nums) { // 앞에서 idx개 이후에 nums들을 추가하기
            int st = 0;
            if (idx == 0) { // 맨 앞에 붙이는 경우
                if (head != null) {
                    Node newNode = getNewNode(nums[0]);
                    newNode.next = head;
                    head = newNode;
                } else {
                    head = getNewNode(nums[0]);
                }
                // 남은 수들을 head 뒤에 차례로 이어붙이기
                idx = 1;
                st = 1;
            }

            Node cur = head;
            // idx개만큼 이동하기
            for (int i = 1; i < idx; i++) {
                cur = cur.next;
            }

            // nums 추가하기
            for (int i = st; i < nums.length; i++) {
                Node newNode = getNewNode(nums[i]);
                newNode.next = cur.next;
                cur.next = newNode;
                cur = newNode;
            }
            // tail 설정하기
            if (cur.next == null) {
                tail = cur;
            }
        }

        void delete(int idx, int cnt) { // idx번 인덱스부터 cnt개 만큼 삭제
            Node cur = head;
            if (idx == 0) {
                for (int i = 0; i < cnt; i++) {
                    cur = cur.next;
                }
                head = cur;
                return;
            }
            // idx만큼 이동하기
            for (int i = 1; i < idx; i++) {
                cur = cur.next;
            }
            Node anchor = cur; // 삭제되기 직전 위치 기억하기

            for (int i = 0; i < cnt; i++) {
                cur = cur.next;
            }
            anchor.next = cur.next;

            if (anchor.next == null) {
                tail = anchor;
            }
        }

        void add(int data) { // 제일 뒤에 data 추가하기
            Node newNode = getNewNode(data);

            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
        }

        void print() throws Exception { // 출력하기
            Node cur = head;
            for (int i = 0; i < 10; i++) {
                System.out.printf("%d ", cur.data);
                cur = cur.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int tc = 1; tc <= 10; tc++) {
            System.out.printf("#%d ", tc);
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            LinkedList linkedList = new LinkedList();

            for (int i = 0; i < N; i++) {
                linkedList.add(Integer.parseInt(st.nextToken()));
            }

            int M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                String cmd = st.nextToken();
                int idx;
                int y;
                int[] nums;

                switch (cmd) {
                    case "I" :
                        idx = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());

                        nums = new int[y];

                        for (int j = 0; j < y; j++) {
                            nums[j] = Integer.parseInt(st.nextToken());
                        }

                        linkedList.insert(idx, nums);
                        break;

                    case "D":
                        idx = Integer.parseInt(st.nextToken());
                        y = Integer.parseInt(st.nextToken());

                        linkedList.delete(idx, y);
                        break;

                    case "A":
                        y = Integer.parseInt(st.nextToken());

                        for (int j = 0; j < y; j++) {
                            linkedList.add(Integer.parseInt(st.nextToken()));
                        }
                        break;
                }
            }
            linkedList.print();
        }
    }
}