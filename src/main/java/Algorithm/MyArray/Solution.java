package Algorithm.MyArray;

import java.lang.reflect.Array;
import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] t = new int[]{0,0,1,1,1,1,2,3,3};
        System.out.println(s.removeDuplicates(t));
        for (int i : t) {
            System.out.print(i);
        }
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> record = new HashMap<>();
        int size = nums.length;
        for (int i = 0; i < size; i++) {
            int now = record.getOrDefault(nums[i], 0);
            now++;
            record.put(nums[i], now);
        }
        int max = 0;
        int k = -1;
        List<Integer> kList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : record.entrySet()) {
            int v = entry.getValue();
            if (v > max) {
                max = v;
                k = entry.getKey();
                kList.clear();
                kList.add(k);
            } else if (v == max) {
                k = entry.getKey();
                kList.add(k);
            }
        }
        int result = Integer.MAX_VALUE;
        for (int k2 : kList) {
            int s = 0;
            int e = 0;
            boolean visit = false;
            for (int i = 0; i < size; i++) {
                if (nums[i] == k2) {
                    if (!visit) {
                        s = i;
                        visit = true;
                    }
                    e = i;
                }
            }
            if (e - s < result) {
                result = e - s;
            }
        }
        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int j = 0; j < nums.length; j++)
            if (i < 2 || nums[j] > nums[i - 2])
                nums[i++] = nums[j];
            else {
                System.out.println(j);
            }
        return i;
    }
}
