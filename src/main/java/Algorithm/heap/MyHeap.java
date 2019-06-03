package Algorithm.heap;

import static Algorithm.sort.QuickSort.swap;

public class MyHeap {
    private int[] a;
    private int n;
    private int count;

    public MyHeap(int capacity) {
        a = new int[capacity];
        n = capacity;
        count = 0;
    }

    public void insert(int data) {
        if (count >= n) {
            return;
        }
        a[count] = data;
        int i = count;
        while ((i + 1) / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }
        ++count;
    }
    public static void main(String[] args) {
        MyHeap myHeap = new MyHeap(3);
        myHeap.insert(2);
        myHeap.insert(3);
        myHeap.insert(4);
        for (int i = 0; i < myHeap.a.length; i++) {
            System.out.print(myHeap.a[i] + " ");
        }
    }

    public static class Heap {
        private int[] a; // 数组，从下标 1 开始存储数据
        private int n;  // 堆可以存储的最大数据个数
        private int count; // 堆中已经存储的数据个数

        public Heap(int capacity) {
            a = new int[capacity + 1];
            n = capacity;
            count = 0;
        }

        public void insert(int data) {
            if (count >= n) return; // 堆满了
            ++count;
            a[count] = data;
            int i = count;
            while (i / 2 > 0 && a[i] > a[i / 2]) { // 自下往上堆化
                swap(a, i, i / 2); // swap() 函数作用：交换下标为 i 和 i/2 的两个元素
                i = i / 2;
            }
        }

        public static void main(String[] args) {
            Heap myHeap = new Heap(3);
            myHeap.insert(2);
            myHeap.insert(3);
            myHeap.insert(4);
            for (int i = 0; i < myHeap.a.length; i++) {
                System.out.print(myHeap.a[i] + " ");
            }
        }
    }


}
