/**
 * 1.4.17. Farthest pair
 * Cho mảng a[] gồm N giá trị double, tìm cặp có hiệu (trị tuyệt đối) lớn nhất.
 * Thuật toán cần có thời gian chạy tuyến tính trong trường hợp tồi nhất.
 */
package week2;

import java.util.Scanner;

public class Bai1_4_17 {

    // Trả về mảng gồm 2 phần tử là cặp xa nhau nhất: {min, max}
    public static double[] farthestPair(double[] a) {
        if (a == null || a.length < 2) {
            throw new IllegalArgumentException("Mang can it nhat 2 phan tu");
        }

        double min = a[0];
        double max = a[0];

        for (int i = 1; i < a.length; i++) {   // O(N)
            if (a[i] < min) min = a[i];
            if (a[i] > max) max = a[i];
        }
        return new double[]{min, max};
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap N: ");
        int n = sc.nextInt();

        double[] a = new double[n];
        System.out.println("Nhap " + n + " so thuc:");
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextDouble();
        }

        double[] pair = farthestPair(a);
        System.out.println("Cap xa nhat: " + pair[0] + " va " + pair[1]);
        System.out.println("Hieu: " + Math.abs(pair[1] - pair[0]));
    }
}