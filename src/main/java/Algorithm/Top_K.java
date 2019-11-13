package Algorithm;

import java.util.concurrent.ThreadLocalRandom;
import java.util.PriorityQueue;
import static Algorithm.sort.QuickSort.quickSort;

public class Top_K {
    //第K大的数字
    private static int topK(int[] A, int size, int K) {
        return find_C(A, 0, size - 1, K);
    }

    public static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    private static int find_C(int[] A, int left, int right, int K) {
        int pivot = A[right];
        int i = left;
        int j = left;
        for (; j != right; j++) {
            if (A[j] > pivot) {
                swap(A, j, i);
                i++;
            }
        }
        swap(A, i, right);
        if (i == K) {
            return A[i];
        } else if (i > K) {
            return find_C(A, left, i - 1, K);
        } else {
            return find_C(A, i + 1, right, K);
        }
    }

    public static void main(String[] args) {
        int len = 40;
//        int[] A = {-1,10,3,2,1,2,};
        int[] A = new int[len];
        int topK = 3;
        for (int i = 0; i < len; i++) {
            A[i] = ThreadLocalRandom.current().nextInt(-3021, 7700);
        }
        int t = (topK(A, len, topK - 1));
        quickSort(A, len);
        for (int i = 0; i != len; i++) {
            System.out.println(A[i]);
        }
        System.out.println(t);
    }
}
