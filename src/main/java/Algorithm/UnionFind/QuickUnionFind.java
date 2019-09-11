package Algorithm.UnionFind;

public class QuickUnionFind {
    //   带路径压缩的 并查集
    int[] roots;

    public QuickUnionFind(int N) {
        roots = new int[N];
        for (int i = 0; i < N; i++) {
            roots[i] = i;
        }
    }

    public void union(int p, int q) {
        int proot = findRoot(p);
        int qroot = findRoot(q);
        roots[proot] = qroot;
    }

    public int findRoot(int p) {
        int root = roots[p];
        while (root != roots[root]) {
            root = roots[root];
        }
        while (p != roots[p]) {
            int tmp = roots[p];
            roots[p] = root;
            p = tmp;
        }
        return root;
    }

    public boolean isConnected(int p, int q) {
        if (findRoot(p) == findRoot(q)) {
            return true;
        }
        return false;
    }

}
