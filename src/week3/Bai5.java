package week3;

import java.util.*;

public class Bai5 {

    static int equalStacks(int[] h1, int[] h2, int[] h3) {
        int sum1 = tongDo(h1);
        int sum2 = tongDo(h2);
        int sum3 = tongDo(h3);

        int i1 = 0, i2 = 0, i3 = 0;

        while (!(sum1 == sum2 && sum2 == sum3)) {
            int max = Math.max(sum1, Math.max(sum2, sum3));

            if (sum1 == max) {
                sum1 -= h1[i1];
                i1++;
            } else if (sum2 == max) {
                sum2 -= h2[i2];
                i2++;
            } else {
                sum3 -= h3[i3];
                i3++;
            }
        }
        return sum1;
    }

    static int tongDo(int[] a) {
        int s = 0;
        for (int x : a) s += x;
        return s;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = sc.nextInt();
        int n2 = sc.nextInt();
        int n3 = sc.nextInt();

        int[] h1 = new int[n1];
        for (int i = 0; i < n1; i++) h1[i] = sc.nextInt();

        int[] h2 = new int[n2];
        for (int i = 0; i < n2; i++) h2[i] = sc.nextInt();

        int[] h3 = new int[n3];
        for (int i = 0; i < n3; i++) h3[i] = sc.nextInt();

        System.out.println(equalStacks(h1, h2, h3));
    }
}