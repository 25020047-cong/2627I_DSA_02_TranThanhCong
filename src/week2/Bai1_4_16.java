/**
 * 1.4.16. Closest pair
 * Cho mảng a[] gồm N giá trị double, tìm cặp có hiệu (trị tuyệt đối) nhỏ nhất.
 * Thời gian chạy: O(N log N) trong trường hợp tệ nhất.
 */
package week2;

import java.util.Arrays;
import java.util.Scanner;

public class Bai1_4_16 {
    public static double[] closestPair(double[] a) {
        if (a == null || a.length < 2) {
            throw new IllegalArgumentException("Mang can it nhat 2 phan tu");
        }

        Arrays.sort(a);           // O(N log N)

        double minDiff = Double.POSITIVE_INFINITY;
        int idx = 0;
        for (int i = 0; i < a.length - 1; i++) {
            double diff = a[i + 1] - a[i];
            if (diff < minDiff) {
                minDiff = diff;
                idx = i;
            }
        }
        return new double[]{a[idx], a[idx + 1]};
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

        double[] pair = closestPair(a);
        System.out.println("Cap gan nhat: " + pair[0] + " va " + pair[1]);
        System.out.println("Hieu: " + Math.abs(pair[1] - pair[0]));
    }
}