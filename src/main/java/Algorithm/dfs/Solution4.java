package Algorithm.dfs;

public class Solution4 {
    int[] dx = new int[]{1, -1, 0, 0};
    int[] dy = new int[]{0, 0, 1, -1};
    boolean[][] visit;

    public static void main(String[] args) {
        Solution4 solution = new Solution4();
        boolean t = solution.exist(new char[][]{{'a', 'b'}}, "ba");
        System.out.println(t);
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m < 1) {
            return false;
        }
        int n = board[0].length;
        visit = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, m, n, 0, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(char[][] board, int i, int j, int m, int n, int pos, String word) {

        if (i < 0 || j < 0 || i >= m || j >= n || word.charAt(pos) != board[i][j]) {
            return false;
        }
        if (pos == word.length() - 1) {
            return true;
        }
        char cur = board[i][j];
        board[i][j] = 0;
        boolean res = false;
        for (int k = 0; k < 4; k++) {
            res |= dfs(board, i + dx[k], j + dy[k], m, n, pos + 1, word);
        }
        board[i][j] = cur;
        return res;
    }
}
