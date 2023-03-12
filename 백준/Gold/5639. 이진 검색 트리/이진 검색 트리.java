import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // 트리에 사용할 Node 만들기
    public static class Node {
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        String next;
        while (true) {
            next = br.readLine();
            if(next == null || next.length() <= 0) break;
            
            Node node = new Node(Integer.parseInt(next));
            addNode(root, node);
        }

        printNode(root);
    }

    // 가장 왼쪽자식으로 쭉 들어가서 프린트하고, 가장 오른쪽 자식으로 들어가서 프린트한 뒤,
    // 둘 다 없을 경우 자기자신을 프린트한다.
    private static void printNode(Node root) {
        if(root == null) return;
        printNode(root.left);
        printNode(root.right);
        System.out.println(root.value);
    }

    // root값과 node값을 비교해서 node값이 더 작을 경우
    // root의 left가 없으면 node를 left로 만들고, 있을 경우 재귀한다.
    // 오른쪽도 마찬가지로 진행
    private static void addNode(Node root, Node node) {
        if(root.value > node.value) {
            if(root.left == null) {
                root.left = node;
            } else {
                addNode(root.left, node);
            }
        } else {
            if(root.right == null) {
                root.right = node;
            } else {
                addNode(root.right, node);
            }
        }
    }
}
