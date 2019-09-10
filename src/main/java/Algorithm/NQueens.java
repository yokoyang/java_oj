package Algorithm;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class NQueens {
    List<List<String>> resFinal = new ArrayList<>();
    List<List<Integer>> res = new ArrayList<>();
    HashSet<Integer> cols = new HashSet<>();
    HashSet<Integer> pie = new HashSet<>();
    HashSet<Integer> na = new HashSet<>();

    public static void main(String[] args) {
        int[] nums = new int[]{7, 2, 5, 10, 8};
        int m = 2;

        NQueens solution = new NQueens();
        int t = solution.splitArray(nums, m);
        System.out.println(t);
//        solution.solveNQueens(4);
    }

    private void convert(int n) {
        for (int i = 0; i < res.size(); i++) {
            List<String> table = new ArrayList<>();
            for (int j = 0; j < res.get(i).size(); j++) {
                int col = res.get(i).get(j);
                StringBuilder strBuilder = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    strBuilder.append(".");
                }
                strBuilder.setCharAt(col, 'Q');
                table.add(strBuilder.toString());
            }
            resFinal.add(table);
        }
    }

    //    N皇后的位运算版本
    private void bitDFS() {

    }

    public List<List<String>> solveNQueens(int n) {
        if (n < 1) {
            return resFinal;
        }
        List<Integer> curState = new ArrayList<>();
        DFS(n, 0, curState);
        convert(n);
        return resFinal;
    }

    private void DFS(int n, int row, List<Integer> curState) {
        if (row >= n) {
            List<Integer> tmp = new ArrayList<>(curState);
            res.add(tmp);
            return;
        }
        for (int col = 0; col < n; col++) {
            if (cols.contains(col) || pie.contains(col + row) || na.contains(row - col)) {
                continue;
            }
            cols.add(col);
            pie.add(col + row);
            na.add(row - col);

            curState.add(col);
            DFS(n, row + 1, curState);
            curState.remove(curState.size() - 1);
            cols.remove(col);
            pie.remove(col + row);
            na.remove(row - col);
        }
    }

    public int splitArray(int[] nums, int m) {
        int size = nums.length;
        if (size == 1) {
            return nums[0];
        }
        int[][] dp = new int[size][m + 1];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        int[] sums = new int[size];
        sums[0] = nums[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }
        dp[0][1] = nums[0];
        for (int i = 0; i < size; i++) {
            dp[i][1] = sums[i];
        }
        for (int i = 1; i < size; i++) {
            for (int j = 2; j <= m; j++) {
                for (int k = 0; k < i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j - 1], sums[i] - sums[k]));
                }
            }
        }

        return dp[size - 1][m];
    }


}
