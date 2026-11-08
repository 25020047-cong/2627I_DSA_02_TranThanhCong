package week9.Bai4;

import java.util.Scanner;

public class BSTLowestCommonAncestor {

    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    // Chèn một đỉnh vào BST (đệ qui) để dựng cây từ dữ liệu nhập
    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }
        if (data < root.data) {
            root.left = insert(root.left, data);
        } else if (data > root.data) {
            root.right = insert(root.right, data);
        }
        return root;
    }

    // Tìm tổ tiên chung gần nhất (đệ qui)
    public static Node lca(Node root, int v1, int v2) {
        if (root == null) {
            return null;
        }
        if (v1 < root.data && v2 < root.data) {
            return lca(root.left, v1, v2);
        }
        if (v1 > root.data && v2 > root.data) {
            return lca(root.right, v1, v2);
        }
        return root;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhap so luong dinh n: ");
        int n = sc.nextInt();

        Node root = null;
        System.out.println("Nhap " + n + " gia tri cua cay:");
        for (int i = 0; i < n; i++) {
            root = insert(root, sc.nextInt());
        }

        System.out.print("Nhap hai dinh v1 v2: ");
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();

        Node ans = lca(root, v1, v2);
        if (ans != null) {
            System.out.println("To tien chung gan nhat cua " + v1 + " va " + v2 + " la: " + ans.data);
        } else {
            System.out.println("Khong tim thay (cay rong).");
        }

        sc.close();
    }
}