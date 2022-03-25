package pers.algorithm.binarytree;

public class RecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int v) {
            value = v;
        }
    }

    // 先序遍历打印所有节点
    public static void preOrder(Node head) {
        if (head == null)
            return;
        System.out.println(head.value);
        preOrder(head.left);
        preOrder(head.right);
    }

    // 中序遍历打印所有节点
    public static void inOrder(Node head) {
        if (head == null)
            return;
        inOrder(head.left);
        System.out.println(head.value);
        inOrder(head.right);
    }

    // 后序遍历打印所有节点
    public static void postOrder(Node head) {
        if (head == null)
            return;
        postOrder(head.left);
        postOrder(head.right);
        System.out.println(head.value);
    }

    // test
    public static void test() {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);

        preOrder(head);
        System.out.println("=================");
        inOrder(head);
        System.out.println("=================");
        postOrder(head);
        System.out.println("=================");
    }

    public static void main(String[] args) {
        test();
    }
}
