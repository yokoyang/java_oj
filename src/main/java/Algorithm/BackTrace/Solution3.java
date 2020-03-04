package Algorithm.BackTrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution3 {
    public static void main(String[] args) {
        Solution3 solution2 = new Solution3();
        solution2.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
    }

    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        trace(candidates, 0, 0, target, new ArrayList<>());
        return res;
    }

    void trace(int[] cand, int s, int nowSum, int target, List<Integer> each) {
        if (nowSum > target) {
            return;
        }
        if (nowSum == target) {
            res.add(new ArrayList<>(each));
        }
        for (int i = s; i < cand.length; i++) {
            if (i > s && cand[i - 1] == cand[i]) {
                continue;
            }
            each.add(cand[i]);
            trace(cand, i + 1, nowSum + cand[i], target, each);
            each.remove(each.size() - 1);
        }
    }
}
