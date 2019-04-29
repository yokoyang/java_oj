package Algorithm;

import java.util.concurrent.ThreadLocalRandom;

public class QuickSort {
    public static void quickSort(int[] A, int size) {
        quickSortC(A, 0, size - 1);
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i]=arr[j];
        arr[j]=t;
    }

    private static void quickSortC(int[] A, int s, int end) {
        if (s >= end) {
            return;
        }
        int q = partition(A, s, end);
        quickSortC(A, s, q - 1);
        quickSortC(A, q + 1, end);
    }

    private static int partition(int[] A, int s, int end) {
        int pivot = A[end];
        int i = s;
        for (int j = s; j != end; j++) {
            if (A[j] < pivot) {
                swap(A, i, j);
                i++;
            }
        }
        swap(A, i, end);
        return i;
    }

    public static void main(String[] args) {
        int len = 10;
        int[] A = new int[len];
        for (int i = 0; i != len; i++) {
            A[i] = ThreadLocalRandom.current().nextInt(150, 7700);
        }
        quickSort(A, len);
        for (int i = 0; i != len; i++) {
            System.out.println(A[i]);
        }

    }
}
