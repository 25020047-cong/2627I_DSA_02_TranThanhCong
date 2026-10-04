package week4.Bai3;

import java.util.*;

public class InsertionSortPart1 {

    public static void insertionSort1(int n, int[] arr) {
        int value = arr[n - 1];
        int j = n - 2;


        while (j >= 0 && arr[j] > value) {
            arr[j + 1] = arr[j];
            printArray(arr);
            j--;
        }


        arr[j + 1] = value;
        printArray(arr);
    }

    static void printArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i > 0) sb.append(' ');
            sb.append(arr[i]);
        }
        System.out.println(sb);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        insertionSort1(n, arr);
    }
}