package Algorithm.dynamicProgramming;

import java.util.Arrays;
import java.util.Collections;

public class Solution {
    public int maxProduct2(int[] nums) {
        int max = Integer.MIN_VALUE;
        int size = nums.length;
        int[][] dp = new int[size][2];
        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < size; i++) {
            if (nums[i] < 0) {
                dp[i][0] = Math.max(dp[i - 1][1] * nums[i],nums[i]);
                dp[i][1] = Math.min(dp[i - 1][0] * nums[i],nums[i]);
            } else {
                dp[i][0] = Math.max(dp[i - 1][0] * nums[i],nums[i]);
                dp[i][1] = Math.min(dp[i - 1][1] * nums[i],nums[i]);
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
}
