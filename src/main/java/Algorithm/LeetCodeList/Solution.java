package Algorithm.LeetCodeList;

public class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == i + 1 || nums[i] <= 0 || nums[i] > nums.length || nums[nums[i] - 1] == nums[i]) {
                i++;
            } else {
                swap(nums, i, nums[i] - 1);
            }
        }
        i = 0;
        while (i < nums.length && nums[i] == i + 1) {
            i++;
        }
        return i + 1;
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ns = new int[]{7, 4, 5, 3, 1, 11, 12};
        solution.firstMissingPositive(ns);
    }
}
