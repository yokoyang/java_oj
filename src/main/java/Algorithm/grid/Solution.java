package Algorithm.grid;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        solution.shiftGrid(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, 1);
        System.out.println(solution.shiftGrid(new int[][]{{1}, {2}, {3}}, 3));
    }

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;

        List<List<Integer>> res = new ArrayList<>();
        int[] last = new int[m];
        while (k > 0) {
            for (int i = 0; i < m; i++) {
                last[i] = grid[i][n - 1];
            }
            for (int j = n - 1; j > 0; j--) {
                for (int i = 0; i < m; i++) {
                    swap(grid, i, j, i, j - 1);
                }
            }

            for (int i = 0; i < m; i++) {
                grid[i][0] = last[i];
            }
            int t = grid[m - 1][0];
            for (int i = m - 1; i > 0; i--) {
                swap(grid, i, 0, i - 1, 0);
            }
            grid[0][0] = t;
            k--;
        }
        for (int i = 0; i < m; i++) {
            ArrayList<Integer> tmp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                tmp.add(grid[i][j]);
            }
            res.add(tmp);
        }
        return res;
    }

    private void swap(int[][] grid, int x1, int y1, int x2, int y2) {
        int t = grid[x1][y1];
        grid[x1][y1] = grid[x2][y2];
        grid[x2][y2] = t;
    }

    //1252
    public int oddCells(int n, int m, int[][] indices) {
        int[][] grid = new int[n][m];
        int[] cols = new int[m];
        int[] rows = new int[n];

        for (int i = 0; i < indices.length; i++) {
            int ri = indices[i][0];
            int ci = indices[i][1];
            rows[ri]++;
            cols[ci]++;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                grid[i][j] += rows[i];
                grid[i][j] += cols[j];
            }
        }
        int num = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if ((grid[i][j] & 1) > 0) {
                    num++;
                }
            }
        }
        return num;
    }
}
