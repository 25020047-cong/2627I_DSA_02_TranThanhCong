package week4;

import java.util.Scanner;

public class Bai7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] count = new int[100];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            count[x]++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            sb.append(count[i]);
            if (i < 99) sb.append(" ");
        }
        System.out.println(sb);
    }
}