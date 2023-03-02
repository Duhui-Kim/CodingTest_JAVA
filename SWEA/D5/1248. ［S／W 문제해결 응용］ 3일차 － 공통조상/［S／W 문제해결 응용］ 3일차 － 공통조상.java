import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Solution {
    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= testCase; t++) {

            int V = sc.nextInt();
            int E = sc.nextInt();
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            // Node를 담을 tree 배열을 생성한다.
            Node[] tree = new Node[V + 1];

            // 각 정점의 번호는 1부터 V까지의 정수이므로 idx에 맞게 노드를 저장한다.
            for (int i = 1; i <= V; i++) {
                tree[i] = new Node(i);
            }

            // 간선이 주어지면 해당 값에 맞게 left child와 right child를 저장한다.
            // 해당 노드의 부모도 저장한다.
            for (int i = 0; i < E; i++) {
                int parent = sc.nextInt();
                int child = sc.nextInt();

                if (tree[parent].left == null) {
                    tree[parent].left = tree[child];
                } else {
                    tree[parent].right = tree[child];
                }
            }

            Node Ancestor = findCommonAncestor(tree[1], tree[n1], tree[n2]);

            sb.append(String.format("#%d %d %d\n", t, Ancestor.value, findchild(Ancestor)));
        }
        System.out.println(sb);
    }

    private static int findchild(Node root) {
        // root가 null이면 없는 노드이므로 0 반환
        if(root == null) return 0;

        // 내려가면서 본인 노드값 +1 해줌
        return findchild(root.right) + findchild(root.left) + 1;
    }

    private static Node findCommonAncestor(Node root, Node n1, Node n2) {
        // root가 null 값이면 null 반환
        if(root == null) return null;

        // root가 n1, n2와 같으면 root 반환
        if(root == n1 && root == n2) return root;

        // 왼쪽에서 n1, n2 찾기
        Node left = findCommonAncestor(root.left, n1, n2);

        // left가 null이 아니고, n1이나 n2도 아니라면 left 반환
        if(left != null && left != n1 && left != n2) return left;

        // 오른쪽에서 n1, n2 찾기
        Node right = findCommonAncestor(root.right, n1, n2);

        // right가 null이 아니고, n1이나 n2도 아니라면 right 반환
        if(right != null && right != n1 && right != n2) return right;

        // 양쪽에서 뭔가를 찾아왔다면 현재 노드가 공통조상
        if(left != null && right != null) return root;

        // 현재 루트가 n1이거나 n2이면 자기 자신을 리턴
        if(root == n1 || root == n2) return root;

        // 그 외에는 양 쪽 중 null이 아닌 값 반환
        if(left != null) return left;
        if(right != null) return right;

        // 양쪽 모두 null이라면 null 반환
        return null;
    }

}