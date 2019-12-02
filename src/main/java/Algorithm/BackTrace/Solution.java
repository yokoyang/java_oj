package Algorithm.BackTrace;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxScoreWords(new String[]{"dad", "dog", "cat", "good"}, new char[]{'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'}, new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
//        s.countServers(new int[][]{{1, 0}, {1, 1}});
//        s.UniqueCombinationSum(new int[]{1, 1, 2, 5, 6, 7, 10}, 8);
//        char[][] boards = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
//        String word = "ABCCED";
//        s.exist(boards, word);
//        s.subsets(new int[]{1, 2, 3, 4});

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

    //    1267. Count Servers that Communicate
    public int countServers(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int val = traceCountServer(grid, i, j);
                    res += val == 1 ? 0 : val;
                }
            }
        }
        return res;
    }

    private int traceCountServer(int[][] grid, int i, int j) {
        grid[i][j] = 0;
        int res = 1;
        int m = grid.length;
        int n = grid[0].length;
        for (int x = 0; x < m; x++) {
            if (grid[x][j] == 1) {
                res += traceCountServer(grid, x, j);
            }
        }
        for (int x = 0; x < n; x++) {
            if (grid[i][x] == 1) {
                res += traceCountServer(grid, i, x);
            }
        }
        return res;
    }

    List<List<Integer>> reconstructMatrixRes = new ArrayList<>();

    //1254. 统计封闭岛屿的数目
    public int closedIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
//                    DFS或者BFS都行
                    res += dfsClosedIsland(grid, i, j);
//                    bfsClosedIsland
                }
            }
        }
        return res;
    }

    //判断是否是封闭岛屿的条件：从岛屿出发，走不到边界
    //如果发现走到边界，那一定不是，但是要注意一点，还是必须要走完才行，否则会把一个大岛屿分成了多个小岛屿
    private int dfsClosedIsland(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length) {
            return 0;
        }
        if (grid[r][c] == 1) {
            return 1;
        }
        grid[r][c] = 1;
        int[] vr = {0, 1, 0, -1};
        int[] vc = {1, 0, -1, 0};
        int ret = 1;
        for (int i = 0; i < 4; i++) {
            ret = Math.min(ret, dfsClosedIsland(grid, r + vr[i], c + vc[i]));
        }
        return ret;
    }

    private int bfsClosedIsland(int[][] grid, int r, int c) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{r, c});
        int res = 1;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1];
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
                res = 0;
                continue;
            }
            if (grid[x][y] != 0) {
                continue;
            }
            grid[x][y] = 1;
            int[] vr = {0, 1, 0, -1};
            int[] vc = {1, 0, -1, 0};
            for (int i = 0; i < 4; i++) {
                int next_x = x + vr[i];
                int next_y = y + vc[i];
                queue.offer(new int[]{next_x, next_y});
            }
        }
        return res;
    }


    //1255. 得分最高的单词集合
//    你将会得到一份单词表 words，一个字母表 letters （可能会有重复字母），以及每个字母对应的得分情况表 score。
//
//请你帮忙计算玩家在单词拼写游戏中所能获得的「最高得分」：能够由 letters 里的字母拼写出的 任意 属于 words 单词子集中，分数最高的单词集合的得分。
//
//单词拼写游戏的规则概述如下：
//
//玩家需要用字母表 letters 里的字母来拼写单词表 words 中的单词。
//可以只使用字母表 letters 中的部分字母，但是每个字母最多被使用一次。
//单词表 words 中每个单词只能计分（使用）一次。
//根据字母得分情况表score，字母 'a', 'b', 'c', ... , 'z' 对应的得分分别为 score[0], score[1], ..., score[25]。
//本场游戏的「得分」是指：玩家所拼写出的单词集合里包含的所有字母的得分之和。
//

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int maxScore;
        int[] record = new int[26];
        for (char c : letters) {
            record[c - 'a']++;
        }
        maxScore = backtrackMaxScoreWords(0, record, words, score);
        return maxScore;
    }

    private int backtrackMaxScoreWords(int index, int[] record, String[] words, int[] score) {
        if (index >= words.length) {
            return 0;
        }
        int[] tmp = Arrays.copyOf(record, record.length);
        int nowScore = 0, res;
        for (int i = 0; i < words[index].length(); i++) {
            char c = words[index].charAt(i);
            if (tmp[c - 'a'] > 0) {
                nowScore += score[c];
                tmp[c]--;
            } else {
                nowScore = 0;
                break;
            }
        }
        res = Math.max(backtrackMaxScoreWords(index + 1, record, words, score), nowScore + backtrackMaxScoreWords(index + 1, tmp, words, score));
        return res;
    }

    //    LeetCode 1247. Minimum Swaps to Make Strings Equal
    //    https://zxi.mytechroad.com/blog/math/leetcode-1247-minimum-swaps-to-make-strings-equal/
    public int minimumSwap(String s1, String s2) {
        int xy = 0;
        int yx = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) == 'x' && s2.charAt(i) == 'y') ++xy;
            if (s1.charAt(i) == 'y' && s2.charAt(i) == 'x') ++yx;
        }
        if (((xy + yx) % 2) > 0) return -1;
        return (xy + 1) / 2 + (yx + 1) / 2;
    }


}
