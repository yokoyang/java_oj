package Algorithm.fenwick_tree;

public class FenwickTree {
    int n;
    int[] sums;

    public FenwickTree(int size) {
        n = size + 1;
        sums = new int[n];
    }

    public void update(int i, int delta) {
        while (i < n) {
            sums[i] += delta;
            i += lowbit(i);

        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += sums[i];
            i -= lowbit(i);
        }
        return sum;
    }

    int lowbit(int x) {
        return x & (-x);
    }

    public static void main(String[] args) {
        FenwickTree fenwickTree = new FenwickTree(5);
        fenwickTree.update(1,2);
        fenwickTree.update(2,3);
        fenwickTree.update(3,4);
        fenwickTree.update(4,5);
        fenwickTree.update(5,6);
        System.out.println(fenwickTree.query(3));
        System.out.println(fenwickTree.query(5));
    }
}
