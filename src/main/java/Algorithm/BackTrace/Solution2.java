package Algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution2 {
    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        solution2.combinationSum(new int[]{2, 3, 6, 7}, 7);
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        int n = candidates.length;
        trace(candidates, 0, 0, target, new ArrayList<>());
        return res;
    }

    void trace(int[] cand, int s, int nowSum, int target, List<Integer> each) {
        if (nowSum > target) {
            return;
        }
        if (nowSum == target) {
            res.add(new ArrayList<>(each));
            return;
        }
        for (int i = s; i < cand.length; i++) {
            each.add(cand[i]);
            trace(cand, i, cand[i] + nowSum, target, each);
            each.remove(each.size() - 1);
        }
    }
}
