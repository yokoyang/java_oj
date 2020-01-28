package Algorithm.fenwick_tree;

class FenwickTree {
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
}
