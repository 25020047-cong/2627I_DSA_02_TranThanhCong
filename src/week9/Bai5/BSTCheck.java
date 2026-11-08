package week9.Bai5;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BSTCheck {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    private static Integer prev;

    public static boolean checkBST(Node root) {
        prev = null;
        return inOrderCheck(root);
    }

    private static boolean inOrderCheck(Node node) {
        if (node == null) {
            return true;
        }

        // 1. Duyệt cây con trái
        if (!inOrderCheck(node.left)) {
            return false;
        }

        // 2. "Thăm" đỉnh hiện tại: phải lớn hơn đỉnh vừa duyệt trước đó
        if (prev != null && node.data <= prev) {
            return false;
        }
        prev = node.data;

        // 3. Duyệt cây con phải
        return inOrderCheck(node.right);
    }

    /**
     * Dựng cây từ dãy nhập theo thứ tự từng tầng (level-order).
     * Ký hiệu "N" nghĩa là không có đỉnh ở vị trí đó.
     */
    private static Node buildTree(Scanner sc, int n) {
        if (n == 0) {
            return null;
        }
        String first = sc.next();
        if (first.equals("N")) {
            return null;
        }

        Node root = new Node(Integer.parseInt(first));
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);

        int count = 1;
        while (!queue.isEmpty() && count < n) {
            Node parent = queue.poll();

            // con trái
            if (count < n) {
                String s = sc.next();
                count++;
                if (!s.equals("N")) {
                    parent.left = new Node(Integer.parseInt(s));
                    queue.add(parent.left);
                }
            }
            // con phải
            if (count < n) {
                String s = sc.next();
                count++;
                if (!s.equals("N")) {
                    parent.right = new Node(Integer.parseInt(s));
                    queue.add(parent.right);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong gia tri (ke ca N): ");
        int n = sc.nextInt();

        System.out.println("Nhap cay theo tung tang (N = khong co dinh):");
        Node root = buildTree(sc, n);

        if (checkBST(root)) {
            System.out.println("Yes - day la cay tim kiem nhi phan");
        } else {
            System.out.println("No - day KHONG phai cay tim kiem nhi phan");
        }

        sc.close();
    }
}