package Algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.UniqueCombinationSum(new int[]{1, 1, 2, 5, 6, 7, 10}, 8);
        char[][] boards = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        s.exist(boards, word);
        s.subsets(new int[]{1, 2, 3, 4});

    }

    //78. Subsets
    //组合问题,没有重复元素
    List<List<Integer>> subsetsRes = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> nowSelect = new ArrayList<>();
        if (nums.length == 0) {
            subsetsRes.add(new ArrayList<>());
            return subsetsRes;
        }
        int startPos = 0;
        subsetDFS(nums, startPos, nowSelect);
        return subsetsRes;
    }

    private void subsetDFS(int[] nums, int pos, List<Integer> nowSelect) {
        subsetsRes.add(new ArrayList<>(nowSelect));
        for (int i = pos; i < nums.length; i++) {
            nowSelect.add(nums[i]);
            subsetDFS(nums, i + 1, nowSelect);
            nowSelect.remove(nowSelect.size() - 1);
        }
    }

    //    90. Subsets II
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        list.add(new ArrayList<>(tempList));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) continue; // skip duplicates
            tempList.add(nums[i]);
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    //    79. Word Search
    //board =
    //[
    //  ['A','B','C','E'],
    //  ['S','F','C','S'],
    //  ['A','D','E','E']
    //]
    //
    //Given word = "ABCCED", return true.
    //Given word = "SEE", return true.
    //Given word = "ABCB", return false.
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        if (m == 0) {
            return false;
        }
        int n = board[0].length;
        char[] words = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfsFindBoard(board, words, i, j, 0, m, n)) {
                    return true;
                }
            }
        }
        return false;

    }
//    39. Combination Sum 可以使用多次
    HashSet<List<Integer>> combinationSumRes = new HashSet<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int size = candidates.length;
        if (size == 0 || target <= 0) {
            return new ArrayList<>(combinationSumRes);
        }
        Arrays.sort(candidates);
        List<Integer> tmp = new ArrayList<>();
        traceCombinationSum(candidates, tmp, 0, target, 0);
        return new ArrayList<>(combinationSumRes);
    }

    private void traceCombinationSum(int[] candidates, List<Integer> tmp, int nowSum, int target, int start) {
        if (nowSum > target) {
            return;
        }
        if (nowSum == target) {
            combinationSumRes.add(new ArrayList<>(tmp));
        }
        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            tmp.add(candidates[i]);
            traceCombinationSum(candidates, tmp, nowSum + candidates[i], target, i);
            tmp.remove(tmp.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        combinationSumBacktrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private void combinationSumBacktrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < nums.length; i++) {
                tempList.add(nums[i]);
                combinationSumBacktrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    //40. Combination Sum II
    //Input: candidates = [10,1,2,7,6,1,5], target = 8,
    //A solution set is:
    //[
    //  [1, 7],
    //  [1, 2, 5],
    //  [2, 6],
    //  [1, 1, 6]
    //]

    public List<List<Integer>> UniqueCombinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        if (target <= 0) {
            return res;
        }
        traceSum3(res, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    private void traceSum3(List<List<Integer>> res, List<Integer> tmp, int start, int[] candidates, int remain) {
        if (remain < 0) {
            return;
        } else if (remain == 0) {
            res.add(new ArrayList<>(tmp));
        }
        for (int i = start; i < candidates.length; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            tmp.add(candidates[i]);
            //这里是i+1，因为不能使用相同的元素了
            traceSum3(res, tmp, i + 1, candidates, remain - candidates[i]);
            tmp.remove(tmp.size() - 1);
        }
    }

    private boolean dfsFindBoard(char[][] board, char[] word, int i, int j, int nowSize, int m, int n) {
        if (nowSize == word.length) {
            return true;
        }
        if (i < 0 || i >= m || j < 0 || j >= n) {
            return false;
        }

        if (board[i][j] != word[nowSize]) {
            return false;
        }
        board[i][j] ^= 256;
        boolean res = dfsFindBoard(board, word, i + 1, j, nowSize + 1, m, n) ||
                dfsFindBoard(board, word, i, j + 1, nowSize + 1, m, n) ||
                dfsFindBoard(board, word, i - 1, j, nowSize + 1, m, n) ||
                dfsFindBoard(board, word, i, j - 1, nowSize + 1, m, n);
        board[i][j] ^= 256;

        return res;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //114. Flatten Binary Tree to Linked List
    //    1
    //   / \
    //  2   5
    // / \   \
    //3   4   6
    //The flattened tree should look like:
    //
    //1
    // \
    //  2
    //   \
    //    3
    //     \
    //      4
    //       \
    //        5
    //         \
    //          6
    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }
        traceFlatten(root);
    }

    private void traceFlatten(TreeNode now) {
        if (now == null) {
            return;
        }
        if (now.left == null) {
            traceFlatten(now.right);
        }
        TreeNode leftRight = now.left;
        if (leftRight == null) {
            return;
        }

        while (leftRight.right != null) {
            leftRight = leftRight.right;
        }


        TreeNode tmp = now.right;
        now.right = now.left;
        now.left = null;
        leftRight.right = tmp;
        traceFlatten(now.right);
    }

    private int pathLength = 0;

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length == 0 || str == null || str.length == 0 || matrix.length != rows * cols || rows <= 0 || cols <= 0 || rows * cols < str.length) {
            return false;
        }
        boolean[] visited = new boolean[rows * cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, str, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, int row, int col, boolean[] visited) {
        boolean flag = false;

        if (row >= 0 && row < rows && col >= 0 && col < cols && !visited[row * cols + col] && matrix[row * cols + col] == str[pathLength]) {
            pathLength++;
            visited[row * cols + col] = true;
            if (pathLength == str.length) {
                return true;
            }
            flag = hasPathCore(matrix, rows, cols, str, row, col + 1, visited) ||
                    hasPathCore(matrix, rows, cols, str, row + 1, col, visited) ||
                    hasPathCore(matrix, rows, cols, str, row, col - 1, visited) ||
                    hasPathCore(matrix, rows, cols, str, row - 1, col, visited);

            if (!flag) {
                pathLength--;
                visited[row * cols + col] = false;
            }
        }

        return flag;
    }


    public int movingCount(int threshold, int rows, int cols) {
        if (threshold < 0 || rows <= 0 || cols <= 0) {
            return 0;
        }
        boolean[][] visit = new boolean[rows][cols];
        return movingCount_C(0, 0, rows, cols, threshold, visit);
    }

    private boolean checkSum(int threshold, int row, int col) {
        int sum = 0;
        while (row != 0) {
            sum += row % 10;
            row = row / 10;
        }
        while (col != 0) {
            sum += col % 10;
            col = col / 10;
        }
        if (sum > threshold) {
            return false;
        }
        return true;
    }

    private int movingCount_C(int startCol, int startRow, int maxRow, int maxCols, int threshold, boolean[][] visit) {
        int moveStep = 0;
        if (startCol >= maxCols || startCol < 0 || startRow >= maxRow || startRow < 0) {
            return moveStep;
        }
        if (!checkSum(threshold, startRow, startCol)) {
            return moveStep;
        }
        if (visit[startRow][startCol]) {
            return moveStep;
        }
        visit[startRow][startCol] = true;
        moveStep += movingCount_C(startCol + 1, startRow, maxRow, maxCols, threshold, visit);
        moveStep += movingCount_C(startCol, startRow + 1, maxRow, maxCols, threshold, visit);
        moveStep += movingCount_C(startCol, startRow - 1, maxRow, maxCols, threshold, visit);
        moveStep += movingCount_C(startCol - 1, startRow, maxRow, maxCols, threshold, visit);
        moveStep += 1;
        return moveStep;
    }
}
