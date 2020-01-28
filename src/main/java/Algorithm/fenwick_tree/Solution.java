package Algorithm.fenwick_tree;

import java.util.*;

public class Solution {
    //    315. Count of Smaller Numbers After Self

    public List<Integer> countSmaller(int[] nums) {
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 0;
        for (int i = 0; i < sorted.length; ++i)
            if (i == 0 || sorted[i] != sorted[i - 1])
                ranks.put(sorted[i], ++rank);

        FenwickTree tree = new FenwickTree(ranks.size());
        List<Integer> ans = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; --i) {
            ans.add(tree.query(ranks.get(nums[i]) - 1));
            tree.update(ranks.get(nums[i]), 1);
        }
        Collections.reverse(ans);
        return ans;
    }
}
