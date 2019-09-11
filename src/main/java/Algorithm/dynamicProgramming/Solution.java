package Algorithm.dynamicProgramming;

import javax.print.DocFlavor;
import java.util.Arrays;
import java.util.Collections;

public class Solution {

    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k <= 0)
            return 0;
        int res = 0;
        if (k >= prices.length / 2) {
            for (int i = 0; i < prices.length - 1; i++)
                if (prices[i + 1] > prices[i])
                    res += (prices[i + 1] - prices[i]);
        } else {
            int[] global = new int[k + 1];
            int[] local = new int[k + 1];
            for (int i = 0; i < prices.length - 1; i++) {
                int diff = prices[i + 1] - prices[i];
                for (int j = k; j >= 1; j--) {
                    local[j] = Math.max(local[j] + diff, global[j - 1] + diff);
                    global[j] = Math.max(local[j], global[j]);
                }
            }
            res = global[k];
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int k = 2;
//        int[] prices = new int[]{2, 1, 2, 0, 1};
        int[] prices = new int[]{1, 2, 4};
        solution.maxProfit(k, prices);
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
}
