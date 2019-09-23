package Algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.permuteUnique(new int[]{1, 1, 2});
        char[][] boards = new char[][]{{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        s.exist(boards, word);
        s.subsets(new int[]{1, 2, 3, 4});

    }

    //78. Subsets
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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        tracePermuteUnique(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }
//47. Permutations II
//    Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//    Input: [1,1,2]
//Output:
//[
//  [1,1,2],
//  [1,2,1],
//  [2,1,1]
//]
//    例如，假设输入的数组为[1，1，2]。则当第一个1被添加进结果集时，可以继续使用第二个1作为元素添加进结果集从而生成112。
//    同理，当试图将第二个1作为第一个元素添加进结果集时，只要第一个1还没有被使用过，则不可以使用第二个1。因此，112不会被重复的添加进结果集。
//其实，这个算法保证了所有重复的数字在结果集中的顺序和在原输入数组中的顺序是相同的。
// 假设将[1,1,2]表示为[1,1#,2],那么结果集中会确保1永远在数值1#的前面，从而避免了11#2和1#12的重复情况出现。

    private void tracePermuteUnique(List<List<Integer>> res, List<Integer> tmp, int[] nums, boolean[] used) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                    continue;
                }
                used[i] = true;
                tmp.add(nums[i]);
                tracePermuteUnique(res, tmp, nums, used);
                tmp.remove(tmp.size() - 1);
                used[i] = false;
            }
        }
    }
}
