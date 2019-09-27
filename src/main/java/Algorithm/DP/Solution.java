package Algorithm.DP;

import java.util.*;

public class Solution {
//    334. Increasing Triplet Subsequence

    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE, secondMin = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= min) min = num;
            else if (num < secondMin) secondMin = num;
            else if (num > secondMin) return true;
        }
        return false;
    }

    //    没有重复数字的序列，返回所有可能的全排序
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int size = nums.length;
        if (size == 0) {
            return res;
        }
        boolean[] record = new boolean[size];
        List<Integer> one = new ArrayList<>();
        dfsPermute(res, one, nums, record);
        return res;
    }

    private void dfsPermute(List<List<Integer>> res, List<Integer> one, int[] nums, boolean[] record) {
        if (one.size() >= nums.length) {
            res.add(new ArrayList<>(one));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (record[i]) {
                continue;
            }
            record[i] = true;
            one.add(nums[i]);
            dfsPermute(res, one, nums, record);
            one.remove(one.size() - 1);
            record[i] = false;
        }
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = 1;
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }
        return dp[m - 1][n - 1];
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        //返回值是List List里面是List 装的String  定义一个HashMap 值为List
        Map<String, List<String>> map = new HashMap<>();
        for (String i : strs) {
            char[] arr = i.toCharArray(); //将字符串转换成数组
            Arrays.sort(arr);          //将字符数组排序
            String str = String.valueOf(arr);
            if (!map.containsKey(str)) {
                map.put(str, new ArrayList<>()); //若不存在建立映射关系 排序后的字符串—>新的List集合（装未排序的异位词）
            }
            map.get(str).add(i);//建立映射关系户后添加 以及存在映射关系后添加 单词
        }
        return new ArrayList<>(map.values());//返回值是List集合 通过构造器 构造一个包含指定 collection 的元素的列表
    }

    //    309. Best Time to Buy and Sell Stock with Cooldown
    public int maxProfit(int[] prices) {
        int m = prices.length;
        if (m < 2) {
            return 0;
        }
        int[] rest = new int[m];
        int[] hold = new int[m];
        int[] sold = new int[m];
        rest[0] = 0;
        hold[0] = -prices[0];
        sold[0] = Integer.MIN_VALUE;
        for (int i = 1; i < m; i++) {
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
            hold[i] = Math.max(rest[i - 1] - prices[i], hold[i - 1]);
            sold[i] = hold[i - 1] + prices[i];
        }
        return Math.max(rest[m - 1], sold[m - 1]);
    }

    //96. Unique Binary Search Trees
    //Given n, how many structurally unique BST's (binary search trees) that store values 1 ... n?
    //
    //Example:
    //
    //Input: 3
    //Output: 5
    //Explanation:
    //Given n = 3, there are a total of 5 unique BST's:
    //
    //   1         3     3      2      1
    //    \       /     /      / \      \
    //     3     2     1      1   3      2
    //    /     /       \                 \
    //   2     1         2                 3

    //    https://www.jianshu.com/p/4570ce2ba076
    //    https://blog.csdn.net/qq_38595487/article/details/79598628
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[i - j - 1] * dp[j];
            }
        }
        return dp[n];
    }

    //    139. 单词拆分
    //给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
    //
    //说明：
    //
    //拆分时可以重复使用字典中的单词。
    //你可以假设字典中没有重复的单词。
    //示例 1：
    //
    //输入: s = "leetcode", wordDict = ["leet", "code"]
    //输出: true
    //解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
    //示例 2：
    //
    //输入: s = "applepenapple", wordDict = ["apple", "pen"]
    //输出: true
    //解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
    //     注意你可以重复使用字典中的单词。
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        HashSet<String> wordSet = new HashSet<>(wordDict);
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak2(String s, List<String> wordDict) {
        return traceWordBreak(s, new HashSet<>(wordDict), 0, new Boolean[s.length()]);
    }

//    private int APEXPackage(int C, int[] wList, int[][] abc) {
//        int[] dp = new int[C];
//
//    }

    public int backPackII(int m, int[] A, int[] V) {
        return backPack(m, A, V);
    }

    private int backPack2(int maxVolume, int[] volumes, int[] values) {
        int objectAmount = volumes.length;
        if (objectAmount == 0) return 0;

        int[] maxValuePerVolume = new int[maxVolume + 1];

        for (int cAmount = 0; cAmount < objectAmount; cAmount++) {
            for (int cVolume = 1; cVolume <= maxVolume; cVolume++) {
                if (cVolume >= volumes[cAmount]) {
                    maxValuePerVolume[cVolume] = Math.max(maxValuePerVolume[cVolume], maxValuePerVolume[cVolume - volumes[cAmount]] + values[cAmount]);
                }
            }
        }

        return maxValuePerVolume[maxVolume];
    }

    private int backPack(int maxVolume, int[] volumes, int[] values) {
        int[][] dp = new int[volumes.length + 1][maxVolume + 1];
        for (int i = 1; i <= volumes.length; i++) {
            for (int j = 1; j <= maxVolume; j++) {
                if (j < volumes[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - volumes[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[volumes.length][maxVolume];
    }

    private boolean traceWordBreak(String s, HashSet<String> wordDict, int start, Boolean[] memo) {
        if (start == s.length()) {
            return true;
        }
        if (memo[start] != null) {
            return memo[start];
        }
        for (int i = start + 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(start, i)) && traceWordBreak(s, wordDict, i, memo)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.backPackII(10, new int[]{2, 3, 5, 7}, new int[]{1, 5, 2, 4});
        Boolean[] booleans = new Boolean[3];

        solution.permute(new int[]{1, 2, 3});
        int[] a = new int[]{1, 2, 3, 0, 2};

        int t = solution.maxProfit(a);
        System.out.println(t);
    }
}
