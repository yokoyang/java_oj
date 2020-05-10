package Algorithm.dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution6 {
    int len = 0;

    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }

    public static void main(String[] args) {

        Solution6 solution = new Solution6();
        solution.fourSum(new int[]{1, 0, -1, 0, -2, 2},
                0);
    }

    private List<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        List<List<Integer>> res = new ArrayList<>();
        if (index == len) {
            return res;
        }
        if (k == 2) {
            int l = index, r = len - 1;
            while (l < r) {
                int v = nums[l] + nums[r];
                if (v == target) {
                    res.add(new ArrayList<>(Arrays.asList(nums[l], nums[r])));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l++;
                    }
                    while (l < r && nums[r] == nums[r + 1]) {
                        r--;
                    }
                } else if (v < target) {
                    l++;
                } else {
                    r--;
                }
            }
        } else {
            for (int i = index; i < len - k + 1; i++) {
                if (i > index && nums[i] == nums[i - 1]) {
                    continue;
                }
                List<List<Integer>> tmp = kSum(nums, target - nums[i], k - 1, i + 1);
                for (List<Integer> one : tmp) {
                    one.add(0, nums[i]);
                }
                res.addAll(tmp);
            }
        }
        return res;
    }
}
