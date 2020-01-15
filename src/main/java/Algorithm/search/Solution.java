package Algorithm.search;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.splitIntoFibonacci("0123"));
    }

    public List<Integer> splitIntoFibonacci(String S) {
        List<Integer> res = new ArrayList<>();
        int size = S.length();
        if (size <= 2) {
            return res;
        }
        if (dfsSplitIntoFibonacci(0, S, res)) {
            return res;
        }
        return new ArrayList<>();
    }

    private boolean dfsSplitIntoFibonacci(int pos, String S, List<Integer> res) {
        if (pos == S.length()) {
            return res.size() >= 3;
        }
        int maxLen = 10;
        if (S.charAt(pos) == '0') {
            maxLen = 1;
        }
        long num = 0;
        for (int i = pos; i < Math.min(pos + maxLen, S.length()); i++) {
            num = num * 10 + (S.charAt(i) - '0');
            if (num > Integer.MAX_VALUE) {
                break;
            }
            if (res.size() >= 2) {
                int sum = res.get(res.size() - 1) + res.get(res.size() - 2);
                if (num > sum) {
                    break;
                } else if (num < sum) {
                    continue;
                }
            }
            res.add((int) num);
            if (dfsSplitIntoFibonacci(i + 1, S, res)) {
                return true;
            }
            res.remove(res.size() - 1);
        }
        return false;
    }
}
