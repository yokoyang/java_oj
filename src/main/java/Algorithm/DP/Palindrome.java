package Algorithm.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Palindrome {
    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
//        System.out.println(palindrome.partition("aab"));
//        System.out.println(palindrome.isPalindrome("aab", 0, 1));
        System.out.println(palindrome.minimumMoves2(new int[]{1, 3, 4, 1, 5}));
//        System.out.println(palindrome.minimumMoves(new int[]{1, 3, 4, 1, 5}));
//        System.out.println(palindrome.palindromePartition2("abc", 2));
//        System.out.println(palindrome.minCut("aab"));
    }

    //    常见回文问题
//    516. Longest Palindromic Subsequence
//        最长回文子序列
    public int longestPalindromeSubseq(String s) {
        int size = s.length();
        int[][] dp = new int[size][size];
        for (int i = 0; i < size; i++) {
            dp[i][i] = 1;
        }
        //长度
        for (int l = 2; l <= size; l++) {
            for (int i = 0; i + l - 1 < size; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i + 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]) + 1;
                }
            }
        }
        return dp[0][size - 1];
    }

    //132. Palindrome Partitioning II
    public int minCut(String s) {
        int size = s.length();
        //valid[i][j]=1 如果s[i~j] Palindromic
        boolean[][] valid = new boolean[size][size];
        //dp[i] => s[0:i]的最小切割次数
        int[] dp = new int[size];
        Arrays.fill(dp, size);
        for (int i = 0; i < size; i++) {
            valid[i][i] = true;
        }
        //长度
        for (int l = 2; l <= size; l++) {
            for (int i = 0; i + l - 1 < size; i++) {
                int j = i + l - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (i + 1 >= j - 1) {
                        valid[i][j] = true;
                    } else {
                        valid[i][j] = valid[i + 1][j - 1];
                    }
                } else {
                    valid[i][j] = false;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (valid[0][i]) {
                dp[i] = 0;
                continue;
            }
            //切割点
            for (int j = 0; j < i; j++) {
                if (valid[j + 1][i]) {
                    dp[i] = Math.min(dp[i], dp[j] + 1);
                }
            }
        }
        return dp[size - 1];

    }

    //    1278. Palindrome Partitioning III
    public int palindromePartition2(String s, int k) {
        int size = s.length();
        if (s.length() <= k) return 0;
        int[][] dp = new int[size][k];
        for (int i = 0; i < size; i++) {
            dp[i][0] = changeToPalindrome(s.substring(0, i + 1));
        }
        for (int j = 1; j < k; j++) {
            for (int i = 0; i < size; i++) {
                int minVal = size;
                for (int p = i - 1; p >= 0; p--) {
                    minVal = Math.min(minVal, dp[p][j - 1] + changeToPalindrome(s.substring(p + 1, i + 1)));
                }
                dp[i][j] = minVal;
            }
        }

        return dp[size - 1][k - 1];
    }

    public int palindromePartition(String s, int k) {
        int size = s.length();
        if (s.length() == k) return 0;
        int[][] dp = new int[size + 1][k];
        for (int i = 0; i < size; i++) {
            dp[i + 1][0] = changeToPalindrome(s.substring(0, i + 1));
        }
        for (int j = 1; j < k; j++) {
            for (int i = j; i <= size; i++) {
                int cur = Integer.MAX_VALUE;
                for (int p = i; p >= j; p--) {
                    cur = Math.min(cur, dp[p - 1][j - 1] + changeToPalindrome(s.substring(p - 1, i)));
                }
                dp[i][j] = cur;
            }
        }

        return dp[size][k - 1];
    }

    private int changeToPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        int counter = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                counter++;
            }
            i++;
            j--;
        }
        return counter;
    }

    // LeetCode 131. Palindrome Partitioning
    //    给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
    //返回 s 所有可能的分割方案。
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        List<String> cur = new ArrayList<>();
        partitionDFS(res, cur, 0, s);
        return res;
    }

    void partitionDFS(List<List<String>> res, List<String> cur, int nowIndex, String s) {
        if (nowIndex >= s.length()) {
            res.add(new ArrayList<>(cur));
            return;
        }
        for (int i = nowIndex; i < s.length(); i++) {
            if (!isPalindrome(s, nowIndex, i)) {
                continue;
            }
            cur.add(s.substring(nowIndex, i + 1));
            partitionDFS(res, cur, i + 1, s);
            cur.remove(cur.size() - 1);
        }
    }

    public boolean isPalindrome(String s, int l, int r) {
        while (l < r) {
            if (s.charAt(l++) != s.charAt(r--)) {
                return false;
            }
        }
        return true;
    }

    //    leetcode-1246-Palindrome Removal
    //    性质题, 对于每一个数字都有两种选择: 1) 选一个和它一样的数字消去, 如果这两个数字的位置之间有其他数字,
    //    当前消去的过程不增加remove的次数 2) 直接消去它自己, remove的次数+1.
    //区间DP或者DFS+记忆
    String getKey(int i, int j) {
        return i + "," + j;
    }

    public int minimumMoves(int[] arr) {
        HashMap<String, Integer> memo = new HashMap<>();
        HashMap<Integer, ArrayList<Integer>> d = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            ArrayList<Integer> t = d.getOrDefault(arr[i], new ArrayList<>());
            t.add(i);
            d.put(arr[i], t);
        }
        return dfsMoves(memo, d, arr, 0, arr.length - 1);
    }

    int dfsMoves(HashMap<String, Integer> memo, HashMap<Integer, ArrayList<Integer>> d, int[] arr, int i, int j) {
        if (i == j) {
            return 1;
        }
        if (i > j) {
            return 0;
        }
        String k = getKey(i, j);
        if (memo.containsKey(k)) {
            return memo.get(k);
        }
        int res = 1 + dfsMoves(memo, d, arr, i + 1, j);
        ArrayList<Integer> sames = d.getOrDefault(arr[i], new ArrayList<>());
        for (Integer s : sames) {
            if (s == i + 1) {
                res = 1 + dfsMoves(memo, d, arr, s + 1, j);
            } else if (i + 1 < s && s <= j) {
                res = Math.min(res, dfsMoves(memo, d, arr, i + 1, s - 1) + dfsMoves(memo, d, arr, s + 1, j));
            }
        }
        memo.put(k, res);
        return res;
    }

    public int minimumMoves2(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n];
        for (int k = 1; k <= n; k++) {
            for (int i = 0; i + k - 1 < n; i++) {
                int j = i + k - 1;
                dp[i][j] = n;
                if (A[i] == A[j]) {
                    if (j - i <= 1) {
                        //说明这个区间只有2个元素
                        dp[i][j] = 1;
                    } else {
                        //最外层匹配,区间不分割。
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                for (int p = i; p < j; p++) {
                    dp[i][j] = Math.min(dp[i][p] + dp[p + 1][j], dp[i][j]);//区间分割
                }
            }
        }
        return dp[0][n - 1];
    }

    //    给定一个字符串 S，找出 S 中不同的非空回文子序列个数，并返回该数字与 10^9 + 7 的模。
    public int countPalindromicSubsequences(String S) {
        int n = S.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        int mod = (int) (1e9 + 7);
        char[] s = S.toCharArray();
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i + l - 1 < n; i++) {
                int j = i + l - 1;
                if (s[i] == s[j]) {
                    int left = i + 1;
                    int right = j - 1;
                    while (left <= right && s[left] != s[i]) {
                        left++;
                    }
                    while (left <= right && s[right] != s[j]) {
                        right--;
                    }
                    if (left < right) {
                        //中间有2个相同值
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[left + 1][right - 1];
                    } else if (left == right) {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }
                } else {
                    dp[i][j] = dp[i + 1][j] + dp[i][j - 1] - dp[i + 1][j - 1];
                }
                dp[i][j] = (dp[i][j] < 0) ? dp[i][j] + mod : dp[i][j] % mod;
            }
        }
        return dp[0][n - 1];
    }
}
