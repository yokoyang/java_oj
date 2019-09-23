package Algorithm.BackTrace;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
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
                if (dfsFindBoard(board, words, i, j, 0, m, n )) {
                    return true;
                }
            }
        }
        return false;

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
}
