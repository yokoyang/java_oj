package Algorithm.sort;

import java.util.concurrent.ThreadLocalRandom;

public class MergeSort {
    private static void mergeSort(int[] A, int n) {
        mergeSort_C(A, 0, n - 1);
    }

    private static void mergeSort_C(int[] A, int p, int r) {
        if (p >= r) {
            return;
        }
        int half = (p + r) / 2;
        mergeSort_C(A, p, half);
        mergeSort_C(A, half + 1, r);
        merge(A, p, half, r);
    }

    private static void merge(int[] A, int p, int half, int r) {
        int left = p;
        int[] temp = new int[r - p+1];
        int temp_pos = 0;
        int right = half + 1;
        for (; left <= half && right <= r; temp_pos++) {
            if (A[left] <= A[right]) {
                temp[temp_pos] = A[left];
                left++;
            } else {
                temp[temp_pos] = A[right];
                right++;
            }
        }
        int now = p;
        while (now <= r) {
            A[now] = temp[now - p];
            now++;
        }
    }

    public static void main(String[] args) {
        int len = 50;
        int[] A = new int[len];
        for (int i = 0; i != len; i++) {
            A[i] = ThreadLocalRandom.current().nextInt(50, 100);
        }
//        System.out.println(A);
        mergeSort(A, len);
        System.out.println("a");

    }
}
