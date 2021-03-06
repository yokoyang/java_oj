package Algorithm.BitSolution;

public class Solution {
    //    136. Single Number
    //Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
    //
    //Example 1:
    //
    //Input: [2,2,1]
    //Output: 1
    //Example 2:
    //
    //Input: [4,1,2,1,2]
    //Output: 4
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(~0<<3));
    }
}
