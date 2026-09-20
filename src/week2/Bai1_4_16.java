package week2;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 1.4.16. Closest pair
 * Cho mảng a[] gồm N giá trị double, tìm cặp có hiệu (trị tuyệt đối) nhỏ nhất.
 * Thời gian chạy: O(N log N) trong trường hợp tệ nhất.
 */
public class Bai1_4_16 {

    /**
     * Trả về mảng 2 phần tử {x, y} (x <= y) là cặp gần nhất trong a[].
     * Ý tưởng: sau khi sắp xếp, cặp gần nhất luôn là hai phần tử liền kề.
     */
    public static double[] closestPair(double[] a) {
        if (a == null || a.length < 2)
            throw new IllegalArgumentException("Can it nhat 2 phan tu");

        int n = a.length;

        // Dùng Double[] để Arrays.sort dùng TimSort (merge sort),
        // được đảm bảo O(N log N) ngay cả trong trường hợp tệ nhất.
        Double[] b = new Double[n];
        for (int i = 0; i < n; i++) b[i] = a[i];
        Arrays.sort(b);

        int best = 0;
        double minDiff = Math.abs(b[1] - b[0]);

        // Duyệt tuyến tính qua các cặp liền kề: O(N)
        for (int i = 1; i < n - 1; i++) {
            double diff = Math.abs(b[i + 1] - b[i]);
            if (diff < minDiff) {
                minDiff = diff;
                best = i;
            }
        }
        return new double[] { b[best], b[best + 1] };
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap N: ");
        int n = sc.nextInt();

        double[] a = new double[n];
        System.out.println("Nhap " + n + " so thuc:");
        for (int i = 0; i < n; i++) a[i] = sc.nextDouble();

        double[] pair = closestPair(a);
        System.out.println("Cap gan nhat: " + pair[0] + " va " + pair[1]);
        System.out.println("Hieu: " + Math.abs(pair[1] - pair[0]));
        sc.close();
    }
}