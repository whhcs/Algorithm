package pers.algorithm.binarytree;

import java.util.Stack;

public class NonRecursiveTraversalBT {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        Node(int value) {
            this.value = value;
        }
    }

    // 前序遍历打印所有节点
    public static void preOrder(Node head) {
        if (head == null)
            return;

        Stack<Node> st = new Stack<>();
        st.push(head);
        while (!st.empty()) {
            head = st.pop();
            System.out.print(head.value + " ");
            if (head.right != null)
                st.push(head.right);
            if (head.left != null)
                st.push(head.left);
        }
        System.out.println();
    }

    // 中序遍历打印所有节点
    public static void inOrder(Node head) {
        if (head == null)
            return;

        Stack<Node> st = new Stack<>();
        while (head != null || !st.empty()) {
            if (head != null) {
                st.push(head);
                head = head.left;
            } else {
                head = st.pop();
                System.out.print(head.value + " ");
                head = head.right;
            }
        }
        System.out.println();
    }

    // 后序遍历打印所有节点，两个栈实现
    public static void postOrder1(Node head) {
        if (head == null)
            return;

        Stack<Node> st1 = new Stack<>();
        Stack<Node> st2 = new Stack<>();
        st1.push(head);
        while (!st1.empty()) {
            head = st1.pop();
            st2.push(head);
            if (head.left != null)
                st1.push(head.left);
            if (head.right != null)
                st1.push(head.right);
        }
        while (!st2.empty()) {
            System.out.print(st2.pop().value + " ");
        }
        System.out.println();
    }

    // 后序遍历打印所有节点，两个栈实现
    public static void postOrder2(Node head) {
        if (head == null)
            return;
        Stack<Node> st = new Stack<>();
        st.push(head);
        Node c;
        while (!st.empty()) {
            c = st.peek();
            if (c.left != null && head != c.left && head != c.right)
                st.push(c.left);
            else if (c.right != null && head != c.right)
                st.push(c.right);
            else {
                System.out.print(st.pop().value + " ");
                head = c;
            }
        }
        System.out.println();
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
        System.out.println("========");
        inOrder(head);
        System.out.println("========");
        postOrder1(head);
        System.out.println("========");
        postOrder2(head);
        System.out.println("========");
    }

    public static void main(String[] args) {
        test();
    }

}
