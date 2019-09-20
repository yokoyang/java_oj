package Algorithm.greedy;

public class Solution {
    //Input: [2,3,1,1,4]
    //Output: true
    //Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
    //贪心算法，记录能够到达的最大值，如果小于i说明中断了，false，如果能到达size-1 ok

    public boolean canJump(int[] nums) {
        int size = nums.length;
        if (size == 0) {
            return false;
        }
        if (size == 1) {
            return true;
        }
        int maxReach = 0;
        for (int i = 0; i < size; i++) {
            if (maxReach < i) {
                return false;
            }
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= size - 1) {
                return true;
            }
        }
        return false;
    }
}
