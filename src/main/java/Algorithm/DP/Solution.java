package Algorithm.DP;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {


    //    没有重复数字的序列，返回所有可能的全排序
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int size = nums.length;
        if (size == 0) {
            return res;
        }
        boolean[] record = new boolean[size];
        List<Integer> each = new ArrayList<>();
        dfsPermute(res, each, nums, record);
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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        tracePermuteUnique(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

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

    //    输入一个字符串,按字典序打印出该字符串中字符的所有排列。
    //    可能有字符重复
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> res = new ArrayList<>();
        if (str == null || str.equals("")) {
            return res;
        }
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        StringBuilder each = new StringBuilder();
        dfsCharPermutation(res, each, chars, new boolean[chars.length]);
        return res;
    }

    private void dfsCharPermutation(ArrayList<String> res, StringBuilder each, char[] chars, boolean[] record) {
        if (each.length() >= chars.length) {
            res.add(each.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (record[i]) {
                continue;
            }
            if (i > 0 && !record[i - 1] && chars[i] == chars[i - 1]) {
                continue;
            }
            each.append(chars[i]);
            record[i] = true;
            dfsCharPermutation(res, each, chars, record);
            each.deleteCharAt(each.length() - 1);
            record[i] = false;
        }

    }

    //    给你一根长度为n的绳子，请把绳子剪成m段（m、n都是整数，n>1并且m>1），
    //    每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
    //    例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
    public int cutRope(int n) {
        int[] dp = new int[n + 1];
        if (n < 2) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 2;
        int max;
        for (int i = 4; i <= n; i++) {
            max = 0;
            //因为是对称的，所有可以是j = i/2开始
            for (int j = i - 1; j >= 1; j--) {
                max = Math.max(dp[j] * (i - j), max);
            }
            dp[i] = max;
        }
        return dp[n];
    }

    private void dfsPermute(List<List<Integer>> res, List<Integer> each, int[] nums, boolean[] record) {
        if (each.size() >= nums.length) {
            res.add(new ArrayList<>(each));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (record[i]) {
                continue;
            }
            record[i] = true;
            each.add(nums[i]);
            dfsPermute(res, each, nums, record);
            each.remove(each.size() - 1);
            record[i] = false;
        }
    }

    // 矩阵中到m,n位置有多少种走法
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

    public int JumpFloor(int target) {
        int[] memo = new int[]{1, 2};
        if (target < 3) {
            return target;
        }
        int result = 0;
        for (int i = 2; i < target; i++) {
            result = memo[0] + memo[1];
            memo[0] = memo[1];
            memo[1] = result;
        }
        return result;
    }

    //    一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。
//    求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        if (target <= 2) {
            return target;
        }
        int[] dp = new int[target + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        int sum;

        for (int i = 3; i <= target; i++) {
            sum = 0;
            for (int j = 0; j < i; j++) {
                sum += dp[j];
            }
            dp[i] = sum;
        }
        return dp[target];
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

    private int maxSum(int[] A) {
        int n = A.length;
        int[] start = new int[n];
        int[] all = new int[n];
        all[n - 1] = A[n - 1];
        start[n - 1] = A[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            start[i] = Math.max(A[i] + start[i + 1], A[i]);
            all[i] = Math.max(start[i], all[i + 1]);
        }
        System.out.println(all[0]);
        return all[0];
    }


    // offer 42
    //连续子数组最大和

    //Input: [-2,1,-3,4,-1,2,1,-5,4],
    //Output: 6
    //Explanation: [4,-1,2,1] has the largest sum = 6.
    //DP的方式
    public int maxSubArray(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }
//        dp 以i结尾的最大序列的max值
        int[] dp = new int[size];
        dp[0] = nums[0];
        for (int i = 1; i < size; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < size; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //    91. Decode Ways
    //A message containing letters from A-Z is being encoded to numbers using the following mapping:
    //输入的是数字
    //    https://zxi.mytechroad.com/blog/dynamic-programming/leetcode-91-decode-ways/
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) != '0' ? 1 : 0;
        for (int i = 2; i <= n; i++) {
            int first = Integer.parseInt(s.substring(i - 1, i));
            int second = Integer.parseInt(s.substring(i - 2, i));
            if (first >= 1 && first <= 9) {
                dp[i] += dp[i - 1];
            }
            if (second >= 10 && second <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }

    //使用滚动数组
    public int numDecodings2(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int w1 = 1, w2 = 1;
        for (int i = 1; i < s.length(); i++) {
            int w = 0;
            if (!isValid(s.charAt(i)) && !isValid(s.charAt(i - 1), s.charAt(i))) {
                return 0;
            }
            if (isValid(s.charAt(i))) {
                w += w1;
            }
            if (isValid(s.charAt(i - 1), s.charAt(i))) {
                w += w2;
            }
            //滚动
            w2 = w1;
            w1 = w;
        }
        return w1;
    }

    private boolean isValid(char c) {
        return c != '0';
    }

    private boolean isValid(char c1, char c2) {
        int num = 10 * (c1 - '0') + (c2 - '0');
        if (num >= 10 && num <= 26) {
            return true;
        }
        return false;
    }

    //使用计划递归，5s
    HashMap<String, Integer> memo = new HashMap<>();

    public int numDecodings3(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        memo.put("", 1);
        return ways(s);
    }

    private int ways(String s) {
        Integer val = memo.get(s);

        if (val != null) {
            return val;
        }
        if (s.charAt(0) == '0') {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        int w = ways(s.substring(1));
        int prefix = Integer.parseInt(s.substring(0, 2));
        //因为这时候第0位已经不可能是0
        if (prefix <= 26) {
            w += ways(s.substring(2));
        }
        memo.put(s, w);
        return w;
    }

    //礼物的最大价值（从矩阵的左上角走到右上角）
    public int maxValueOfGifts(int[][] values) {
        if (values == null || values.length <= 0 || values[0].length <= 0) {
            return 0;
        }
        int rows = values.length;
        int cols = values[0].length;
        int[][] dp = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int up = 0;
                int left = 0;
                if (i > 0) {
                    up = dp[i - 1][j];
                }
                if (j > 0) {
                    left = dp[i][j - 1];
                }
                dp[i][j] = Math.max(left, up) + values[i][j];
            }
        }
        return dp[rows - 1][cols - 1];
    }

    //剑指offer 48
    //最长不含重复字符的子字符串
    public int longestSubstringWithoutDuplication(String str) {
        int curLength = 0;
        int maxLength = 0;
        int[] position = new int[26];
        for (int i = 0; i < str.length(); i++) {
            int preIndex = position[str.charAt(i) - 'a'];
            if (preIndex < 0 || i - preIndex > curLength) {
                curLength++;
            } else {
                if (curLength > maxLength) {
                    maxLength = curLength;
                }
                curLength = i - preIndex;
            }
            position[str.charAt(i) - 'a'] = i;
        }
        if (curLength >= maxLength) {
            maxLength = curLength;
        }
        return maxLength;
    }

    public String[] printProbability(int n) {
        if (n <= 0)
            return null;
        //结果可能性总数
        int total = (int) Math.pow(6, n);
        String[] result = new String[6 * n - n + 1];

        //dp[c][k] c个骰子朝上一面点数之和为k的次数
        int[][] dp = new int[n + 1][6 * n + 1];
        //初始化dp[1][1...6]
        for (int x = 1; x <= 6; x++)
            dp[1][x] = 1;
        //执行计算
        for (int i = 2; i <= n; i++)
            for (int j = 2; j <= 6 * n; j++) {
                int sum = 0;
                for (int m = 1; m < j && m <= 6; m++)
                    sum += dp[i - 1][j - m];
                dp[i][j] = sum;
            }
        //统计结果,用分数表示
        for (int k = n; k <= 6 * n; k++) {
            result[k - n] = dp[n][k] + "/" + total;
        }
        return result;
    }
    //    1155. 掷骰子的N种方法
    //这里有 d 个一样的骰子，每个骰子上都有 f 个面，分别标号为 1, 2, ..., f。
    //我们约定：掷骰子的得到总点数为各骰子面朝上的数字的总和。
    //如果需要掷出的总点数为 target，请你计算出有多少种不同的组合情况（所有的组合情况总共有 f^d 种），模 10^9 + 7 后返回。

    public int numRollsToTarget(int d, int f, int target) {
        int toMod = (int) Math.pow(10, 9) + 7;
        if (target > d * f) {
            return 0;
        }
        if (target < d) {
            return 0;
        }
        int[][] dp = new int[d + 1][target + 1];
        for (int i = 1; i <= Math.min(f, target); i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= d; i++) {
            for (int j = i; j <= target; j++) {
                int sum = 0;
                for (int k = 1; k <= f; k++) {
                    if (j - k >= 0) {
                        sum += dp[i - 1][j - k];
                        sum %= toMod;
                    }
                }
                dp[i][j] = sum;
            }
        }
        return dp[d][target];
    }

    //    1269. Number of Ways to Stay in the Same Place After Some Steps
    public int numWays(int steps, int arrLen) {
        int toMod = (int) Math.pow(10, 9) + 7;
        int min = Math.min(steps, arrLen);
        long[][] dp = new long[steps + 1][min];
        dp[1][0] = 1;
        dp[1][1] = 1;
        for (int i = 2; i <= steps; i++) {
            for (int j = 0; j < min; j++) {
                dp[i][j] += dp[i - 1][j];
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < min - 1) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= toMod;
            }
        }
        return (int) dp[steps][0];
    }

    //1262. Greatest Sum Divisible by Three
    public int maxSumDivThree(int[] nums) {
        int[] dp = new int[3];
        int[] tmp = new int[3];
        int modRes;
        for (int n : nums) {
            for (int v : dp) {
                modRes = (n + v) % 3;
                if (modRes == 0) {
                    tmp[0] = Math.max(n + v, tmp[0]);
                } else if (modRes == 1) {
                    tmp[1] = Math.max(n + v, tmp[1]);
                } else {
                    tmp[2] = Math.max(n + v, tmp[2]);
                }
            }
            dp = Arrays.copyOf(tmp, 3);
        }
        return dp[0];
    }


    public int maxSumDivThree2(int[] nums) {
        int[] dp = new int[3];
        for (int i = 0; i < nums.length; i++) {
            int mod = nums[i] % 3;
            int a = dp[(3 - mod) % 3];
            int b = dp[(3 + 1 - mod) % 3];
            int c = dp[(3 + 2 - mod) % 3];
            if (a != 0 || mod == 0) {
                dp[0] = Math.max(dp[0], a + nums[i]);
            }
            if (b != 0 || mod == 1) {
                dp[1] = Math.max(dp[1], b + nums[i]);
            }
            if (c != 0 || mod == 2) {
                dp[2] = Math.max(dp[2], c + nums[i]);
            }
        }
        return dp[0];
    }


    //    1278. Palindrome Partitioning III
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

    //    1292. Maximum Side Length of a Square with Sum Less than or Equal to Threshold
    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 0; i + k <= m && j + k <= n; k++) {
                    if (sumRange(dp, i, j, i + k, j + k) <= threshold) {
                        max = Math.max(max, k + 1);
                    }
                }
            }
        }
        return max;
    }

    private int sumRange(int[][] dp, int i1, int j1, int i2, int j2) {
        return Math.abs(dp[i2][j2] - dp[i1 - 1][j2] - dp[i2][j1 - 1] + dp[i1 - 1][j1 - 1]);
    }


    public int[] pathsWithMaxScore(List<String> boards) {
        int kMod = (int) (1e9 + 7);
        int n = boards.size();
        int[][] dp = new int[n + 1][n + 1];
        int[][] paths = new int[n + 1][n + 1];
        char[][] board = new char[n][n];
        for (int i = 0; i < boards.size(); i++) {
            for (int j = 0; j < boards.get(i).length(); j++) {
                board[i][j] = boards.get(i).charAt(j);
            }
        }
        board[n - 1][n - 1] = '0';
        board[0][0] = '0';
        paths[n - 1][n - 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (board[i][j] != 'X') {
                    int m = Math.max(Math.max(dp[i + 1][j], dp[i][j + 1]), dp[i + 1][j + 1]);
                    dp[i][j] = (board[i][j] - '0') + m;
                    if (dp[i + 1][j] == m)
                        paths[i][j] = (paths[i][j] + paths[i + 1][j]) % kMod;
                    if (dp[i][j + 1] == m)
                        paths[i][j] = (paths[i][j] + paths[i][j + 1]) % kMod;
                    if (dp[i + 1][j + 1] == m)
                        paths[i][j] = (paths[i][j] + paths[i + 1][j + 1]) % kMod;
                }
            }
        }
        if (paths[0][0] == 0) {
            return new int[]{0, 0};
        }
        return new int[]{dp[0][0], paths[0][0]};
    }


    public int longestCommonSubsequence(String text1, String text2) {
        if (text1.length() == 0 || text2.length() == 0) return 0;
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];

        for (int i = 1; i < text1.length() + 1; i++) {
            for (int j = 1; j < text2.length() + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    //if match, add lcs +1
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //if no match, skip
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }

    public int minInsertions(String s) {
        int n = s.length();
        String reverse = new StringBuilder(s).reverse().toString();
        return n - longestCommonSubsequence(s, reverse);
    }

    public int minInsertions2(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int l = 2; l <= n; l++) {
//            l 当前选定长度
            for (int p = 0; p < n; p++) {
//                p起点 q终点
                int q = p + l - 1;
                if (p >= q || q >= n) {
                    continue;
                }
                if (s.charAt(p) == s.charAt(q)) {
                    dp[p][q] = dp[p + 1][q - 1];
                } else {
                    dp[p][q] = Math.min(dp[p][q - 1], dp[p + 1][q]) + 1;
                }
            }
        }
//        for (int l = 2; l <= n; ++l)
//            for (int i = 0, j = l - 1; j < n; ++i, ++j)
//                dp[i][j] = s.charAt(i) == s.charAt(j) ? dp[i + 1][j - 1] : Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
        return dp[0][n - 1];
    }

    //    shortest-common-supersequence
//    LeetCode - 1092.

    public String shortestCommonSupersequence(String str1, String str2) {
        //    首先先构造最长公共子序列LCS dp 数组
        int len1 = str1.length();
        int len2 = str2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int l1 = len1, l2 = len2;
        while (l1 > 0 || l2 > 0) {
            char c = ' ';
            if (l1 == 0) {
                c = str2.charAt(--l2);
            } else if (l2 == 0) {
                c = str1.charAt(--l1);
            } else if (str1.charAt(l1 - 1) == str2.charAt(l2 - 1)) {
                c = str1.charAt(l1 - 1);
                l1--;
                l2--;
            } else if (dp[l1][l2] == dp[l1 - 1][l2]) {
                c = str1.charAt(l1 - 1);
                l1--;
            } else if (dp[l1][l2] == dp[l1][l2 - 1]) {
                c = str2.charAt(l2 - 1);
                l2--;
            }
            sb.append(c);
        }
        return sb.reverse().toString();

    }

    public int maxProfit2(int[] prices) {
        int maxChances = 2;
        int days = prices.length;
        if (days <= 1) {
            return 0;
        }
        int[][][] dp = new int[days + 1][maxChances + 1][2];
        for (int i = 0; i <= maxChances; i++) {
            dp[0][i][1] = Integer.MIN_VALUE;
//            dp[1][i][1] = -prices[0];
        }
        for (int i = 1; i <= days; i++) {
            for (int j = 1; j <= maxChances; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i - 1], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i - 1], dp[i - 1][j][1]);
            }
        }
        int ans = 0;
        for (int i = 1; i <= maxChances; i++) {
            ans = Math.max(ans, dp[days][i][0]);
        }
        return ans;
    }

    public int maxProfit3(int k, int[] prices) {
        int ans = 0;
        int days = prices.length;
        if (days <= 1) {
            return 0;
        }
        if (k * 2 >= days) {
            return greedyProfit(prices);
        }
        int[][][] dp = new int[days + 1][k + 1][2];
        for (int i = 0; i <= k; i++) {
            dp[0][i][1] = Integer.MIN_VALUE;
        }
        for (int i = 1; i <= days; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j][0] = Math.max(dp[i - 1][j][1] + prices[i - 1], dp[i - 1][j][0]);
                dp[i][j][1] = Math.max(dp[i - 1][j - 1][0] - prices[i - 1], dp[i - 1][j][1]);
            }
        }
        for (int i = 1; i <= k; i++) {
            ans = Math.max(ans, dp[days][i][0]);
        }
        return ans;
    }


    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int size = nums.length;
        int[][] dp = new int[size][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < size; i++) {
            if (nums[i] < 0) {
                dp[i][0] = Math.max(dp[i - 1][1] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][0] * nums[i], nums[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0] * nums[i], nums[i]);
                dp[i][1] = Math.min(dp[i - 1][1] * nums[i], nums[i]);
            }
        }
        for (int i = 0; i < size; i++) {
            if (dp[i][0] > max) {
                max = dp[i][0];
            }
        }
        return max;
    }

    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;

        if (nums.length == 1) {
            return Math.max(nums[0], 0);
        }
        int maxN = 1;
        int minN = 1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0) {
                int t = maxN;
                maxN = minN;
                minN = t;
            }
            maxN = Math.max(maxN * nums[i], nums[i]);
            minN = Math.min(minN * nums[i], nums[i]);
            if (max < maxN) {
                max = maxN;
            }
        }
        return max;
    }

    public int lengthOfLIS(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return 0;
        }
        int ans = 1;
        if (size == 1) {
            return ans;
        }
        int[] dp = new int[size];
        dp[0] = 1;
        for (int i = 0; i < size; i++) {
            int maxval = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    maxval = Math.max(dp[i - 1], maxval);
                }
            }
            dp[i] = maxval + 1;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int coinChange(int[] coins, int amount) {
        if (amount < 0) {
            return -1;
        }
        if (amount == 0) {
            return 0;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int c : coins) {
                if (i - c < 0) {
                    continue;
                }
                if (dp[i - c] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - c] + 1, dp[i]);
                }
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }

    public int minDistance(String word1, String word2) {
        int size1 = word1.length();
        int size2 = word2.length();
        if (size1 == 0) {
            return size2;
        }
        if (size2 == 0) {
            return size1;
        }
        int[][] dp = new int[size1 + 1][size2 + 1];
        for (int i = 0; i < size1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (int i = 0; i < size1 + 1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j < size2 + 1; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i < size1 + 1; i++) {
            for (int j = 1; j < size2 + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                }
            }
        }
        return dp[size1 - 1][size2 - 1];
    }

    public int maxProfitCoolDown(int[] prices) {
        int days = prices.length;
        if (days <= 1) {
            return 0;
        }
        int[] rest = new int[days + 1];
        int[] hold = new int[days + 1];
        int[] sold = new int[days + 1];
        hold[0] = -prices[0];
        sold[0] = Integer.MIN_VALUE;
        for (int i = 1; i <= days; i++) {
            rest[i] = Math.max(rest[i - 1], sold[i - 1]);
            hold[i] = Math.max(hold[i - 1], rest[i - 1] - prices[i - 1]);
            sold[i] = hold[i - 1] + prices[i - 1];
        }
        return Math.max(rest[days], sold[days]);
    }

    public int greedyProfit(int[] prices) {
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            int t = prices[i] - prices[i - 1];
            if (t > 0) {
                ans += t;
            }
        }
        return ans;
    }

    public int maxProfitTransactionFee(int[] prices, int fee) {
        int days = prices.length;
        int[][] dp = new int[days + 1][2];
        dp[0][1] = Integer.MIN_VALUE / 2;
        for (int i = 1; i <= days; i++) {
            dp[i][0] = Math.max(dp[i - 1][1] + prices[i - 1] - fee, dp[i - 1][0]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i - 1], dp[i - 1][1]);
        }
        return dp[days][0];
    }

    public int longestSubsequence(int[] arr, int difference) {
        if (arr.length <= 1) {
            return arr.length;
        }
        HashMap<Integer, Integer> dp = new HashMap<>();
        int ans = 1;
        for (int n : arr) {
            int pre = n - difference;
            int times = dp.getOrDefault(pre, 0) + 1;
            dp.put(n, times);
            ans = Math.max(ans, times);
        }
        return ans;
    }

    private long maxSubArray(int[] arr, int r) {
        long ans = 0, sum = 0;
        for (int l = 0; l < r; l++) {
            for (long n : arr) {
                sum = Math.max(sum + n, 0);
                ans = Math.max(ans, sum);
            }
        }
        return ans;
    }


    public int kConcatenationMaxSum(int[] arr, int k) {
        int mod = (int) (1e9 + 7);
        long ans1 = maxSubArray(arr, 1);
        if (k == 1) {
            return (int) (ans1 % mod);
        }
        long sum = 0;
        for (int n : arr) {
            sum += n;
        }
        sum = Math.max(0, sum);
        long ans2 = maxSubArray(arr, 2);
        return (int) Math.max(Math.max(ans1, ans2), (k - 2) * sum + ans2) % mod;
    }

    public double largestSumOfAverages(int[] A, int K) {
        int n = A.length;
        double[][] memo = new double[n][n];
        double[][] dp = new double[n + 1][K + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = getAvg(A, 0, i - 1, memo);
        }
        for (int i = 1; i <= n; i++) {
            for (int g = 2; g <= K; g++) {
                for (int j = i - 1; j >= g - 1; j--) {
                    dp[i][g] = Math.max(dp[i][g], dp[j][g - 1] + getAvg(A, j, i - 1, memo));
                }
            }
        }
        return dp[n][K];
    }

    private double getAvg(int[] A, int s, int e, double[][] memo) {
        if (memo[s][e] > 0) {
            return memo[s][e];
        }
        int c = (e - s) + 1;
        double sum = 0.0;
        for (int i = s; i <= e; i++) {
            sum += A[i];
        }
        double res = sum / c;
        memo[s][e] = res;
        return res;
    }

    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        int[][] record = new int[n][2];
        for (int i = 0; i < n; i++) {
            record[i] = new int[]{arr[i], i};
        }
        Arrays.sort(record, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int[] each : record) {
            int h = each[0];
            int pos = each[1];
            int r = Math.min(n - 1, pos + d);
            int l = Math.max(0, pos - d);
            for (int j = pos + 1; j <= r && arr[j] < h; j++) {
                dp[pos] = Math.max(dp[pos], dp[j] + 1);
            }
            for (int j = pos - 1; j >= l && arr[j] < h; j--) {
                dp[pos] = Math.max(dp[pos], dp[j] + 1);
            }
        }
        int ans = 1;
        for (int v : dp) {
            ans = Math.max(ans, dp[v]);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.largestSumOfAverages(new int[]{1, 2, 3, 4, 5, 6, 7},4));
        System.out.println(solution.largestSumOfAverages(new int[]{1, 2}, 1));
//        System.out.println(solution.minInsertions2("mbadm"));
//        List<String> input = new ArrayList<>();
//        input.add("E23");
//        input.add("2X2");
//        input.add("12S");
//        System.out.println(solution.pathsWithMaxScore(input));
//        int t = solution.maxSumDivThree(new int[]{3, 6, 5, 1, 8});
//        int t = solution.maxSumDivThree(new int[]{1, 2, 3, 4, 4});
//        System.out.println(t);
//        int nw = solution.numWays(2, 4);
//        System.out.println(nw);
//        String[] result = solution.printProbability(3);
//        System.out.println(result.length);
//        for (String val : result)
//            System.out.print(val + ",");
//        solution.longestSubstringWithoutDuplication("arabcacfr");
//        solution.JumpFloorII(4);
//        solution.Permutation("");
//        solution.maxSum(new int[]{0, -2, 3, 5, -1, 2});
//        solution.backPackII(10, new int[]{2, 3, 5, 7}, new int[]{1, 5, 2, 4});
//        Boolean[] booleans = new Boolean[3];
//
//        solution.permute(new int[]{1, 2, 3});
//        int[] a = new int[]{1, 2, 3, 0, 2};
//
//        int t = solution.maxProfit(a);
//        System.out.println(t);
    }
}
