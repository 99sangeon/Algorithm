package DFS;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// https://www.acmicpc.net/problem/5639 (이진 검색 트리)
public class BOJ_5639 {

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Node root = new Node(Integer.parseInt(br.readLine()));

        int cnt = 10000;
        while (cnt > 0) {
            try {
                int num = Integer.parseInt(br.readLine());
                // 입력받은 num을 적절한 위치에 삽입
                insertNode(num, root);
            } catch (NumberFormatException e) {
                break;
            }
        }

        postOrder(root);

        System.out.println(sb);
    }

    private static void postOrder(Node root) {
        if(root == null) return;

        postOrder(root.left);
        postOrder(root.right);
        sb.append(root.num).append("\n");
    }

    private static void insertNode(int num, Node parent) {
        if(num < parent.num) {
            if(parent.left == null) {
                parent.left = new Node(num);
            } else {
                insertNode(num, parent.left);
            }
        } else if (num > parent.num) {
            if(parent.right == null) {
                parent.right = new Node(num);
            } else {
                insertNode(num, parent.right);
            }
        }
    }

    private static class Node {
        int num;
        Node left;
        Node right;

        public Node(int num) {
            this.num = num;
        }
    }
}
