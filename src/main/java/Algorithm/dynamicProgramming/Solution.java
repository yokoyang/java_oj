package Algorithm.dynamicProgramming;

public class Solution {
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
