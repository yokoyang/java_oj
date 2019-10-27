package Algorithm.division;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.diffWaysToCompute("2-1-1");
    }

    private HashMap<String, List<Integer>> memo = new HashMap<>();

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


    public List<Integer> diffWaysToCompute(String input) {
        if (memo.containsKey(input)) {
            return memo.get(input);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1, input.length()));
                for (Integer l : left) {
                    for (Integer r : right) {
                        if (ch == '+') {
                            res.add(l + r);
                        } else if (ch == '-') {
                            res.add(l - r);
                        } else if (ch == '*') {
                            res.add(l * r);
                        }
                    }
                }
            }
        }
        if (res.size() == 0) {
            res.add(Integer.valueOf(input));
        }
        memo.putIfAbsent(input, res);
        return res;
    }
}
