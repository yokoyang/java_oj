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
