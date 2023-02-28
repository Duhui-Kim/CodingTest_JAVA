import java.util.*;

public class Solution {
    public static void main(String[] args) {

        // 이렇게 하는거 아닌 것 같지만 풀음..

        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int testCase = sc.nextInt();

        // testCase만큼 반복을 진행한다.
        for (int t = 1; t <= testCase; t++) {
            int V = sc.nextInt();
            int E = sc.nextInt();
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            // child Node로부터 가장 가까운 부모노드를 찾기 위해 listA와 listB를 만든다.
            ArrayList<Integer> listA = new ArrayList<>();
            ArrayList<Integer> listB = new ArrayList<>();

            // 시작 노드가 부모 노드가 될 수도 있으니 list에 각각 추가해준다.
            listA.add(n1);
            listB.add(n2);

            // 간선을 저장할 tree 배열을 만든다.
            int[][] tree = new int[E][2];
            
            // 0번에는 부모, 1번에는 자식노드를 입력받아 저장한다.
            for (int i = 0; i < E; i++) {
                int num = sc.nextInt();
                int num2 = sc.nextInt();

                tree[i][0] = num;
                tree[i][1] = num2;
            }

            // n1을 자식노드로 갖는 부모노드를 찾고, 리스트에 넣는다.
            // 그 부모노드를 자식노드로 갖는 부모노드를 찾고.. 반복
            // 최종적으로 맨 위의 노드를 자식노드로 갖는 부모노드는 없으므로 종료된다.
            int findNum = n1;
            int preSize = -1;
            while (preSize != listA.size()) {
                preSize = listA.size();
                for (int i = 0; i < E; i++) {
                    if (tree[i][1] == findNum) {
                        listA.add(tree[i][0]);
                        findNum = tree[i][0];
                    }
                }
            }

            // n2도 마찬가지로 진행하며 list에 값을 넣는다.
            findNum = n2;
            preSize = -1;
            while (preSize != listB.size()) {
                preSize = listB.size();
                for (int i = 0; i < E; i++) {
                    if (tree[i][1] == findNum) {
                        listB.add(tree[i][0]);
                        findNum = tree[i][0];
                    }
                }
            }
            
            // listA의 0번 idx는 n1을 자식으로 갖는 노드, 1번 노드는 0번 노드의 부모노드 ...
            // 따라서 가장 가까운 부모노드는 idx가 앞쪽에 있을수록 가깝다.
            // 해당 값과 listB의 부모노드 중 일치하는 값을 찾는다.
            loop:
            for (int i = 0; i < listA.size(); i++) {
                for (int j = 0; j < listB.size(); j++) {
                    if (listA.get(i).equals(listB.get(j))) {
                        findNum = listA.get(i);
                        break loop;
                    }
                }
            }

            // listA 재사용 위해 clear 진행
            listA.clear();
            
            // listA에 위에서 구한 부모노드를 넣는다.
            listA.add(findNum);
            preSize = -1;
            
            // cnt는 자기자신도 cnt하므로 1부터 시작하여 자녀 노드의 수를 구한다.
            int cnt = 1;
            while (preSize != listA.size()) {
                preSize = listA.size();
                
                // list에 있는 수가 부모노드인 배열을 찾고, 그 자녀노드를 list에 넣는다.
                // cnt도 증가시킨다.
                // 이 때, 중복체크를 방지하기 위해 해당 노드의 값을 -1로 바꿔놓는다.
                for (int i = 0; i < E; i++) {
                    if (listA.contains(tree[i][0])) {
                        listA.add(tree[i][1]);
                        cnt++;
                        int[] arr = {-1, -1};
                        tree[i] = arr;
                    }
                }
            }
            // 결과를 출력한다.
            sb.append(String.format("#%d %d %d\n", t, findNum, cnt));
        }
        System.out.println(sb);
    }
}

