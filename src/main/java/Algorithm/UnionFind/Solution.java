package Algorithm.UnionFind;

import java.util.HashSet;

public class Solution {
    public int numIslands(char[][] grid) {
        int m = grid.length;
        if (m == 0) {
            return 0;
        }
        int n = grid[0].length;
        if (n == 0) {
            return 0;
        }

        int counter = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    counter++;
                    pain(m, n, i, j, grid);
                }
            }
        }
        return counter;
    }

    private void pain(int m, int n, int i, int j, char[][] grid) {
        if (i >= m || j >= n || i < 0 || j < 0) {
            return;
        }

        if (grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';

        pain(m, n, i + 1, j, grid);
        pain(m, n, i, j + 1, grid);
        pain(m, n, i - 1, j, grid);
        pain(m, n, i, j - 1, grid);
    }

    public int findCircleNum(int[][] M) {
        int m = M.length;
        if (m == 0) {
            return 0;
        }
        int n = M[0].length;
        if (n == 0) {
            return 0;
        }

        QuickUnionFind quickUnionFind = new QuickUnionFind(m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                if (M[i][j] == 1) {
                    quickUnionFind.union(i, j);
                }
            }
        }
        int counter = 0;
        HashSet<Integer> record = new HashSet<>();
        for (int i = 0; i < m; i++) {
            record.add(quickUnionFind.findRoot(i));
        }
        counter = record.size();
        return counter;
    }

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        int[] p = new int[n];
        for (int i = 0; i < n; i++) {
            p[i] = i;
        }
        for (int[] c : connections) {
            p[find(p, c[0])] = p[find(p, c[1])];
        }
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(p, p[i]));
        }
        return set.size() - 1;
    }

    private int find(int[] p, int x) {
        if (p[x] == x) {
            return x;
        }
        p[x] = find(p, p[x]);
        return p[x];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int t = solution.findCircleNum(new int[][]{{1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0}, {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0}, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}});
        System.out.println(t);
    }
}
