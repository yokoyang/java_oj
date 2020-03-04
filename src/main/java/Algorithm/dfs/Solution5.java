package Algorithm.dfs;

import java.util.Arrays;

public class Solution5 {
    int[] dx = new int[]{-1, 1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    int m, n;
    int res = 0;

    public static void main(String[] args) {
        Solution5 solution5 = new Solution5();
        int t = solution5.maxAreaOfIsland(new int[][]{{1, 1, 0, 0, 0}, {1, 1, 0, 0, 0}, {0, 0, 0, 1, 1}, {0, 0, 0, 1, 1}});
        System.out.println(t);
    }

    public int maxAreaOfIsland(int[][] grid) {
        m = grid.length;
        if (m == 0) {
            return 0;
        }
        n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }

    int dfs(int[][] grid, int y, int x) {
        if (y < 0 || y >= m || x < 0 || x >= n) {
            return 0;
        }
        if (grid[y][x] != 1) {
            return 0;
        }
        grid[y][x] = -1;
        int space = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            space += dfs(grid, ny, nx);
        }
        if (space>4){
            System.out.println("a");
        }
        res = Math.max(space, res);
        return space;
    }
}
