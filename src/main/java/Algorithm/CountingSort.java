package Algorithm;

import java.util.concurrent.ThreadLocalRandom;

public class CountingSort {
    public static void countingSort(int[] a, int n) {
        if (n <= 1) {
            return;
        }
        int max = a[0];
        for (int i = 1; i != n; i++) {
            if (max < a[i]) {
                max = a[i];
            }
        }
        int min = a[0];
        for (int i = 1; i != n; i++) {
            if (min > a[i]) {
                min = a[i];
            }
        }
        int gap = 0 - min;
        max = max + gap;
        int[] c = new int[max + 1];
        for (int i = 0; i <= max; ++i) {
            c[i] = 0;
        }

        //计算元素个数
        for (int i = 0; i < n; ++i) {
            c[a[i] + gap]++;
        }
        for (int i = 1; i <= max; ++i) {
            c[i] = c[i - 1] + c[i];
        }
        int[] r = new int[n];
        for (int i = n - 1; i >= 0; --i) {
            int index = c[a[i] + gap] - 1;
            r[index] = a[i];
            c[a[i] + gap]--;
        }
        System.arraycopy(r, 0, a, 0, n);

    }

    public static void main(String[] args) {
        int len = 40;
//        int[] A = {-1,10,3,2,1,2,};
        int[] A = new int[len];
        for (int i = 0; i != len; i++) {
            A[i] = ThreadLocalRandom.current().nextInt(-3021, 7700);
        }
        countingSort(A, len);
        for (int i = 0; i != len; i++) {
            System.out.println(A[i]);
        }
    }
}
