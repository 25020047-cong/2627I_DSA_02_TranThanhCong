package week5;

import java.util.Arrays;
import java.util.Random;

public class Bai2 {

    static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int key = a[i];
            int j = i - 1;
            while (j >= 0 && a[j] > key) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }

    static void mergeSort(int[] a) {
        int[] aux = new int[a.length];
        mergeSort(a, aux, 0, a.length - 1);
    }

    private static void mergeSort(int[] a, int[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(a, aux, lo, mid);
        mergeSort(a, aux, mid + 1, hi);
        if (a[mid] <= a[mid + 1]) return;
        merge(a, aux, lo, mid, hi);
    }

    private static void merge(int[] a, int[] aux, int lo, int mid, int hi) {
        System.arraycopy(a, lo, aux, lo, hi - lo + 1);
        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if (i > mid) a[k] = aux[j++];
            else if (j > hi) a[k] = aux[i++];
            else if (aux[j] < aux[i]) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    static final String[] TYPES = {
            "Ngẫu nhiên", "Tăng dần", "Giảm dần", "Gần như sắp xếp", "Nhiều phần tử trùng"
    };

    static int[] generate(int type, int n, long seed) {
        Random rnd = new Random(seed);
        int[] a = new int[n];
        switch (type) {
            case 0: // ngẫu nhiên
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(n * 10);
                break;
            case 1: // tăng dần
                for (int i = 0; i < n; i++) a[i] = i;
                break;
            case 2: // giảm dần
                for (int i = 0; i < n; i++) a[i] = n - i;
                break;
            case 3: // gần như sắp xếp
                for (int i = 0; i < n; i++) a[i] = i;
                for (int k = 0; k < Math.max(1, n / 100); k++) {
                    int x = rnd.nextInt(n), y = rnd.nextInt(n);
                    int t = a[x]; a[x] = a[y]; a[y] = t;
                }
                break;
            case 4: // nhiều phần tử trùng
                for (int i = 0; i < n; i++) a[i] = rnd.nextInt(10);
                break;
        }
        return a;
    }

    interface Sorter { void sort(int[] a); }

    static double time(Sorter s, int[] data, int runs) {
        long total = 0;
        for (int r = 0; r < runs; r++) {
            int[] copy = data.clone();
            long start = System.nanoTime();
            s.sort(copy);
            total += System.nanoTime() - start;
            if (!isSorted(copy)) throw new IllegalStateException("Sắp xếp sai!");
        }
        return total / (double) runs / 1_000_000.0;
    }

    static boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) if (a[i - 1] > a[i]) return false;
        return true;
    }

    public static void main(String[] args) {
        int[] sizes = {1_000, 5_000, 10_000, 50_000, 100_000};
        int runs = 5;

        for (int i = 0; i < 3; i++) {
            int[] w = generate(0, 5_000, 1);
            insertionSort(w.clone());
            mergeSort(w.clone());
        }

        System.out.printf("%-22s %10s %18s %16s %10s%n",
                "Loại dữ liệu", "Kích thước", "InsertionSort(ms)", "MergeSort(ms)", "Nhanh hơn");
        System.out.println("-".repeat(82));

        for (int t = 0; t < TYPES.length; t++) {
            for (int n : sizes) {
                int[] data = generate(t, n, 42);

                double ins = time(Bai2::insertionSort, data, runs);
                double mer = time(Bai2::mergeSort, data, runs);

                String winner = ins < mer ? "Insertion" : "Merge";
                System.out.printf("%-22s %10d %18.3f %16.3f %10s%n",
                        TYPES[t], n, ins, mer, winner);
            }
            System.out.println();
        }

        int[] test = generate(0, 10_000, 7);
        int[] expected = test.clone();
        Arrays.sort(expected);
        mergeSort(test);
        System.out.println("MergeSort đúng so với Arrays.sort: " + Arrays.equals(test, expected));
    }
}