package Algorithm.leetcodeDeque;

import java.util.*;

public class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) {
            return new int[0];
        }
        Comparator<Integer> bigComparator = (i1, i2) -> (i2 - i1);
        int[] result = new int[nums.length - k + 1];
        int nowMax;
        PriorityQueue<Integer> windows = new PriorityQueue<>(bigComparator);
        for (int i = 0; i < nums.length; i++) {
            windows.add(nums[i]);
            if (windows.size() >= k) {
                nowMax = windows.peek();
                windows.remove(nums[i - k + 1]);
                result[i - k + 1] = nowMax;
            }

        }
        return result;
    }

    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length == 0) return new int[]{};
        // deque is a window of at most size k that ends at index i
        Deque<int[]> dq = new ArrayDeque<>();
        int[] ret = new int[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            // This keeps the deque a decreasing sequence
            while (!dq.isEmpty() && dq.getLast()[1] <= nums[i])
                dq.removeLast();

            // Add new element to deque
            dq.offerLast(new int[]{i, nums[i]});

            // Kick out elements that are not no longer in the window
            if (!dq.isEmpty() && dq.getFirst()[0] <= i - k) {
                dq.removeFirst();
            }

            // pick the first one in the window
            if (i >= k - 1) {
                ret[i - k + 1] = dq.getFirst()[1];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] result = s.maxSlidingWindow2(new int[]{1, 3, 4, 2, -3, -1, 5, 3, 6, 7}, 3);
        for (int i = 0; i < result.length; i++) {
            System.out.println(
                    result[i]
            );
        }
    }
}
