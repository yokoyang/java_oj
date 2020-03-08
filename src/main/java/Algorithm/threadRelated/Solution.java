package Algorithm.threadRelated;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.numOfMinutes(15,
                0,
                new int[]{-1, 0, 0, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6},
                new int[]{1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    HashMap<String, Boolean> record = new HashMap<>();

    String getKey(String s, String p) {
        return s + "#" + p;
    }

    HashMap<Integer, ArrayList<Integer>> record2;

    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        record = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            int v = manager[i];
            if (v < 0) {
                continue;
            }
            ArrayList<Integer> list = record2.getOrDefault(v, new ArrayList<>());
            list.add(i);
            record2.put(v, list);
        }
        return trace(informTime, headID);
    }

    int trace(int[] informTime, int nowPos) {
        int max = 0;
        ArrayList<Integer> each = record2.getOrDefault(nowPos, new ArrayList<>());
        for (int v : each) {
            max = Math.max(max, trace(informTime, v));
        }
        return max + informTime[nowPos];
    }

    public boolean isMatch(String s, String p) {
        StringBuilder p2 = new StringBuilder();
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && p.charAt(i) == p.charAt(i - 1) && p.charAt(i) == '*') {
                continue;
            }
            p2.append(p.charAt(i));
        }
        return trace(s, p2.toString());
    }

    boolean trace(String s, String p) {
        String key = getKey(s, p);
        if (record.containsKey(key)) {
            return record.get(key);
        }
        if (s.equals(p)) {
            record.put(key, true);
            return true;
        }
        if (p.equals("*")) {
            record.put(key, true);
            return true;
        }
        if (s.equals("") || p.equals("")) {
            record.put(key, false);
            return false;
        }
        if (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?') {
            boolean t = trace(s.substring(1), p.substring(1));
            record.put(key, t);
            return t;
        }
        if (p.charAt(0) == '*') {
            boolean t = trace(s, p.substring(1)) || trace(s.substring(1), p);
            record.put(key, t);
            return t;
        }
        return false;
    }
}
