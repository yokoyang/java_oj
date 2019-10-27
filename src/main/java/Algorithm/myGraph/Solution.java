package Algorithm.myGraph;

public class Solution {
    private void explore(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length) {
            return;
        }
        if (j < 0 || j >= grid[i].length) {
            return;
        }
        if (grid[i][j] != '1') {
            return;
        }
        grid[i][j] = '0';
        explore(grid, i + 1, j);
        explore(grid, i - 1, j);
        explore(grid, i, j + 1);
        explore(grid, i, j - 1);
    }

    public int numIslands(char[][] grid) {
        int counter = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    explore(grid, i, j);
                    counter++;
                }
            }
        }
        return counter;
    }
}
